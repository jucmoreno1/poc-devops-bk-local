//Definición de variables
def versionImage = '1.0.'
def applicationName = 'poc-devops-bk-local'
//Namespace de kubernetes
def namespace = 'mynamespace'
def projectGCP = 'gcpcert-272801'
//def projectGCP = 'com-poc-ci-cd'


pipeline {
    agent any

    stages {
        stage('Construcción Imagen') {
            steps {
                echo 'Inicia construcción del proyecto...'
				sh 'cd source && chmod +x gradlew'
                sh 'cd source && ./gradlew clean build'
                echo 'Armando la imagen para subir a Google Cloud Platform'
                sh "docker build -f docker/Dockerfile -t  gcr.io/${projectGCP}/${applicationName}:${versionImage}${env.BUILD_NUMBER} ."
            }
        }
        stage('Push a GCP') {
            steps {
                echo 'Inicia el envío de la imagen al Container Registry...'
                sh "docker push gcr.io/${projectGCP}/${applicationName}:${versionImage}${env.BUILD_NUMBER}"
            }
        }
        stage('Pruebas Unitarias') {
            steps {
                echo 'Se lanzan las pruebas unitarias...'
                echo 'Se realizaron las pruebas, bueno no hay...'
                sh 'cd source && ./gradlew check'
            }
        }
        stage('Desplegado') {
            steps {
                echo 'Comienza desplegado en desarrollo...'
                echo 'Se crea el namespace si no existe'
                sh "kubectl get ns ${namespace} || kubectl create ns ${namespace}"
                //echo 'Update the imagetag to the latest version'
                //sh "sed -i.bak 's#.*gcr.io.*#        image: gcr.io/${projectGCP}/${applicationName}:${versionImage}${env.BUILD_NUMBER}#'  kubernetes/deployment.yaml"
                // le agregamos manualmente el nombre del proyecto ya que tenemos la imagen del cloudenpoint -> project ID: gcpcert-272801
                sh "sed -i.bak 's#.*gcr.io/gcpcert-272801.*#        image: gcr.io/${projectGCP}/${applicationName}:${versionImage}${env.BUILD_NUMBER}#'  kubernetes/deployment.yaml"
                //sh cscript replace.vbs "deployment.yaml" "gcr.io" "gcr.io/${projectGCP}/${applicationName}:${versionImage}${env.BUILD_NUMBER}"
				echo 'Create or update resources'
                sh "kubectl apply -f kubernetes/deployment.yaml"
                sh "kubectl apply -f kubernetes/service.yaml"
            }
        }
    }

    post {
        always {
            echo 'Se cargan los resultados de las pruebas unitarias...'
            //junit 'build/test-results/**/*.xml'
        }
    }
}
