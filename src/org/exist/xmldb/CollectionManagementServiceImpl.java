
package org.exist.xmldb;
import java.io.IOException;
import java.util.Vector;
import org.apache.xindice.client.xmldb.services.CollectionManager;
import org.apache.xmlrpc.*;
import org.w3c.dom.Document;

import org.xmldb.api.base.*;
import org.xmldb.api.modules.CollectionManagementService;


public class CollectionManagementServiceImpl extends CollectionManager implements CollectionManagementService {

    protected XmlRpcClient client;
    protected CollectionImpl parent = null;

    public CollectionManagementServiceImpl( CollectionImpl parent, XmlRpcClient client ) {
        this.client = client;
        this.parent = parent;
    }

    public Collection createCollection( String collName ) throws XMLDBException {
        String name = collName;
        if ( ( !collName.startsWith( "/db" ) ) && parent != null )
            name = parent.getPath() + "/" + collName;

        Vector params = new Vector();
        params.addElement( name );
        try {
            client.execute( "createCollection", params );
        } catch ( XmlRpcException xre ) {
            throw new XMLDBException( ErrorCodes.VENDOR_ERROR,
                xre.getMessage() );
        } catch ( IOException ioe ) {
            throw new XMLDBException( ErrorCodes.VENDOR_ERROR,
                ioe.getMessage() );
        }
        CollectionImpl collection =
            new CollectionImpl( client, (CollectionImpl) parent, null, name );
        parent.addChildCollection( collection );
        return collection;
    }


    /**
     *  Implements createCollection from interface CollectionManager. Gets
     *  called by some applications based on Xindice.
     *
     *@param  path                Description of the Parameter
     *@param  configuration       Description of the Parameter
     *@return                     Description of the Return Value
     *@exception  XMLDBException  Description of the Exception
     */
    public Collection createCollection( String path, Document configuration )
         throws XMLDBException {
        return createCollection( path );
    }

    public String getName() throws XMLDBException {
        return "CollectionManagementService";
    }

    public String getProperty( String property ) {
        return null;
    }

    public String getVersion() throws XMLDBException {
        return "1.0";
    }

    public void removeCollection( String collName ) throws XMLDBException {
        String name = collName;
        if ( !collName.startsWith( "/" ) )
            name = parent.getPath() + '/' + collName;

        Vector params = new Vector();
        params.addElement( name );
        try {
            client.execute( "removeCollection", params );
        } catch ( XmlRpcException xre ) {
            throw new XMLDBException( ErrorCodes.VENDOR_ERROR,
                xre.getMessage() );
        } catch ( IOException ioe ) {
            throw new XMLDBException( ErrorCodes.VENDOR_ERROR,
                ioe.getMessage() );
        }
        parent.removeChildCollection( collName );
    }

    public void setCollection( Collection parent ) throws XMLDBException {
        this.parent = (CollectionImpl) parent;
    }

    public void setProperty( String property,
                             String value ) {
    }

}

