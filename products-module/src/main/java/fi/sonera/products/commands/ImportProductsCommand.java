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

    @Inject
    public ImportProductsCommand(SystemContext systemContext){

        this.systemContext = systemContext;
    }



    @Override
    public boolean execute(Context context) throws Exception {
        return false;
    }
}
