package com.uaic.lab7.internationalization;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

@Named
@SessionScoped
public class LanguageBean implements Serializable {
    private Locale locale;

    @PostConstruct
    public void init() {
        locale = new Locale("en");
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
