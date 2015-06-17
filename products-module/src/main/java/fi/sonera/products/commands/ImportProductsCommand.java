package fi.sonera.products.commands;

import com.google.inject.Inject;
import info.magnolia.commands.MgnlCommand;
import info.magnolia.context.Context;
import info.magnolia.context.SystemContext;

/**
 * Created by kdewitte on 17/06/15.
 */
public class ImportProductsCommand extends MgnlCommand{

    private SystemContext systemContext;

    /**
     *  Session session = systemContext.getJCRSession("tweets");
     Node root =  session.getNode("/");
     Node node = root.addNode(id_str,"mgnl:tweet");

     ImportGenericJson genericJson = new ImportGenericJson() {
    @Override
    protected String treatThis(String key, JSONArray object) {
    if(key.equals("hashtags")){
    List<String> value=new ArrayList<>();
    for(int i=0;i<object.length();i++){
    try {
    value.add((String)((JSONObject)object.get(i)).get("text"));
    } catch (JSONException e) {
    e.printStackTrace();
    }
    }
    return StringUtils.join(value,",");
    }
    return String.valueOf(object);
    }
    };

     genericJson.importJsonToNode(tweet,node,"mgnl:content");

     session.save();
     * @param systemContext
     */




    @Inject
    public ImportProductsCommand(SystemContext systemContext){

        this.systemContext = systemContext;
    }



    @Override
    public boolean execute(Context context) throws Exception {
        return false;
    }
}
