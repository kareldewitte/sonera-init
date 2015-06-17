package fi.sonera.products.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.AccessDeniedException;
import javax.jcr.InvalidItemStateException;
import javax.jcr.ItemExistsException;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.ReferentialIntegrityException;
import javax.jcr.RepositoryException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.version.VersionException;
import java.util.Iterator;

/**
 * Created by kdewitte on 22/05/15.
 */
public abstract class ImportGenericJson {

    private static final Logger log = LoggerFactory.getLogger(ImportGenericJson.class);


    public  void importJsonToNode(JSONObject rootObject,Node node,String nodeType){
           persist(rootObject,node,nodeType);
    }


    private  void persist(JSONObject rootObject,Node node,String nodeType){


        Iterator keys = rootObject.keys();
        try {

            while (keys.hasNext()) {
                String key = (String) keys.next();
                Object object = rootObject.get(key);
                log.debug("key {}, value {}", key, object);
                if (object instanceof JSONObject) {
                    //wil be node
                    Node kid = null;
                    if(!node.hasNode(key)) {
                        kid = node.addNode(key, nodeType);
                        node.getSession().save();
                    }else{
                        kid = node.getNode(key);
                    }
                    //do a recursive call on underlying map
                    persist((JSONObject) object,kid,nodeType);
                } else {
                    //will be property and value
                    if(object instanceof Boolean){
                        node.setProperty(key,(Boolean)object);
                    }else
                    if(object instanceof JSONArray) {
                        node.setProperty(key, treatThis(key, (JSONArray) object));
                    }
                    else{
                        node.setProperty(key,String.valueOf(object));
                    }


                }
                node.getSession().save();
            }
        } catch (ReferentialIntegrityException | InvalidItemStateException | NoSuchNodeTypeException | LockException | VersionException | AccessDeniedException | ConstraintViolationException | ItemExistsException | PathNotFoundException e) {
            e.printStackTrace();
        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected abstract String treatThis(String key, JSONArray object);

}
