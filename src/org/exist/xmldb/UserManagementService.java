
package org.exist.xmldb;
import org.exist.security.Permission;

import org.exist.security.User;
import org.xmldb.api.base.*;

/**
 *  An eXist-specific service which provides methods to manage users and
 *  permissions.
 *
 *@author     Wolfgang Meier <meier@ifs.tu-darmstadt.de>
 *@created    20. August 2002
 */
public interface UserManagementService extends Service {

    /**
     *  Get the name of this service
     *
     *@return    The name
     */
    public String getName();


    /**
     *  Get the version of this service
     *
     *@return    The version value
     */
    public String getVersion();


    /**
     *  Change owner and group of the current collection.
     *
     *@param  u                   Description of the Parameter
     *@param  group               Description of the Parameter
     *@exception  XMLDBException  Description of the Exception
     */
    public void chown( User u, String group ) throws XMLDBException;


    /**
     *  Change owner and group of the specified resource.
     *
     *@param  res                 Description of the Parameter
     *@param  u                   Description of the Parameter
     *@param  group               Description of the Parameter
     *@exception  XMLDBException  Description of the Exception
     */
    public void chown( Resource res, User u, String group )
         throws XMLDBException;


    /**
     *  Change permissions for the specified resource.
     *
     * Permissions are specified in a string according to the
     * following format:
     * 
     * <pre>[user|group|other]=[+|-][read|write|update]</pre>
     * 
     * For example, to grant all permissions to the group and
     * deny everything to others:
     * 
     * group=+write,+read,+update,other=-read
     * 
     * The changes are applied to the permissions currently
     * active for this resource.
     * 
     *@param  resource            Description of the Parameter
     *@param  modeStr             Description of the Parameter
     *@exception  XMLDBException  Description of the Exception
     */
    public void chmod( Resource resource, String modeStr ) throws XMLDBException;

    /**
     *  Change permissions for the current collection
     *
     *@param  modeStr             String describing the permissions to
     * grant or deny.
     *@exception  XMLDBException
     * 
     */
    public void chmod( String modeStr ) throws XMLDBException;
    
    /**
     *  Add a new user to the database
     *
     *@param  user                The feature to be added to the User attribute
     *@exception  XMLDBException  Description of the Exception
     */
    public void addUser( User user ) throws XMLDBException;


    /**
     *  Update existing user information
     *
     *@param  user                Description of the Parameter
     *@exception  XMLDBException  Description of the Exception
     */
    public void updateUser( User user ) throws XMLDBException;


    /**
     *  Get a user record from the database
     *
     *@param  name                Description of the Parameter
     *@return                     The user value
     *@exception  XMLDBException  Description of the Exception
     */
    public User getUser( String name ) throws XMLDBException;


    /**
     *  Retrieve a list of all existing users.
     *
     *@return                     The users value
     *@exception  XMLDBException  Description of the Exception
     */
    public User[] getUsers() throws XMLDBException;


    /**
     *  Get a property defined by this service.
     *
     *@param  property            Description of the Parameter
     *@return                     The property value
     *@exception  XMLDBException  Description of the Exception
     */
    public String getProperty( String property )
         throws XMLDBException;


    /**
     *  Set a property for this service.
     *
     *@param  property            The new property value
     *@param  value               The new property value
     *@exception  XMLDBException  Description of the Exception
     */
    public void setProperty( String property, String value )
         throws XMLDBException;


    /**
     *  Set the current collection for this service
     *
     *@param  collection          The new collection value
     *@exception  XMLDBException  Description of the Exception
     */
    public void setCollection( Collection collection ) throws XMLDBException;


    /**
     *  Get permissions for the specified collections
     *
     *@param  coll                Description of the Parameter
     *@return                     The permissions value
     *@exception  XMLDBException  Description of the Exception
     */
    public Permission getPermissions( Collection coll ) throws XMLDBException;


    /**
     *  Get permissions for the specified resource
     *
     *@param  res                 Description of the Parameter
     *@return                     The permissions value
     *@exception  XMLDBException  Description of the Exception
     */
    public Permission getPermissions( Resource res ) throws XMLDBException;


    /**
     * Get permissions for all resources contained in the current
     * collection. Returns a list of permissions in the same order
     * as Collection.listResources().
     * 
     * @return Permission[]
     * @throws XMLDBException
     */
    public Permission[] listResourcePermissions() throws XMLDBException;
    
    /**
     * Get permissions for all child collections contained in the current
     * collection. Returns a list of permissions in the same order
     * as Collection.listChildCollections().
     * 
     * @return Permission[]
     * @throws XMLDBException
     */
    public Permission[] listCollectionPermissions() throws XMLDBException;

    /**
     *  Delete a user from the database
     *
     *@param  name                Description of the Parameter
     *@exception  XMLDBException  Description of the Exception
     */
    public void removeUser( String name ) throws XMLDBException;
}

