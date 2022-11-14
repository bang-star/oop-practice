package org.example.mvc.view;

import static org.example.mvc.view.RedirectView.DEFAULT_REDRIECT_PREFIX;

public class JSPViewResolver implements ViewResolver{
    @Override
    public View resolveView(String viewname) {
        if(viewname.startsWith(DEFAULT_REDRIECT_PREFIX)){
            return new RedirectView(viewname);
        }else{
            return new JspView(viewname+".jsp");
        }
    }
}
