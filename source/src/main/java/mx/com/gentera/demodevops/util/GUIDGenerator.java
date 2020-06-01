/**
 * 
 */
package mx.com.gentera.demodevops.util;

/** Shamelessly ripped from generated XDoclet source, because I don't want to generate util classes.
 * 
 * @author XDoclet.sf.net
 * @version $Id: GUIDGenerator.java 5585 2008-05-01 20:55:00Z anatom $
 */
public final class GUIDGenerator 
	{

    /** Cached per JVM server IP. */
    private static String hexServerIP = null;

    // initialise the secure random instance
    private static final java.security.SecureRandom seeder = new java.security.SecureRandom();
	 
	//Private constructor, this class no generate instance
	private GUIDGenerator( )
		{
		}
     
     /**
      * A 32 byte GUID generator (Globally Unique ID). These artificial keys SHOULD <strong>NOT </strong> be seen by the user,
      * not even touched by the DBA but with very rare exceptions, just manipulated by the database and the programs.
      *
      * Usage: Add an id field (type java.lang.String) to your EJB, and add setId(XXXUtil.generateGUID(this)); to the ejbCreate method.
      */
	public static final String generateGUID() {
		return generateGUID(new Object());
	}
	
    public static final String generateGUID(Object object) 
		{
    	//El objeto referencia debe estar instanciado
    	if(object == null) {
    		return generateGUID();
    	}
    	
        final java.lang.StringBuffer tmpBuffer = new java.lang.StringBuffer( 16 );
         if ( hexServerIP == null ) 
			{
            java.net.InetAddress localInetAddress = null;
            try {
                localInetAddress = java.net.InetAddress.getLocalHost();
				} 
			catch( java.lang.Exception exception ) 
				{
            	hexServerIP = hexFormat( getInt( "127.0.0.1".getBytes( ) ), 8 );
				}
             byte serverIP[] = localInetAddress != null ? localInetAddress.getAddress( ) : "127.0.0.1".getBytes() ;
             hexServerIP = hexFormat( getInt( serverIP ), 8 );
			}

        final java.lang.String hashcode = hexFormat( System.identityHashCode( object ), 8 );
        tmpBuffer.append(hexServerIP);
        tmpBuffer.append(hashcode);

        long timeNow      = System.currentTimeMillis();
        int timeLow       = (int)timeNow & 0xFFFFFFFF;
        int node          = seeder.nextInt();

        final java.lang.StringBuffer 
						guid = new java.lang.StringBuffer( 32 );
						guid.append( hexFormat( timeLow, 8 ) );
						guid.append( tmpBuffer.toString( ) );
						guid.append( hexFormat( node, 8 ) );
         return guid.toString();
		}
     
    private static int getInt(byte bytes[]) 
		{
        int i = 0;
        int j = 24;
        for (int index = 0; j >= 0; index++) 
			{
             int l = bytes[ index ] & 0xff;
             i += l << j;
             j -= 8;
			}
        return i;
		}

    private static String hexFormat(int i, int j) 
		{
        final java.lang.String hexadecimal = Integer.toHexString( i );
        return padHex( hexadecimal, j ) + hexadecimal;
		}

    private static String padHex( String cadena, int i ) 
		{
        final java.lang.StringBuffer tmpBuffer = new java.lang.StringBuffer( );
        if (cadena.length() < i) 
			{
            for (int j = 0; j < i - cadena.length( ); j++) 
				{
                tmpBuffer.append( '0' );
				}
			}
        return tmpBuffer.toString( );
		}
	}

