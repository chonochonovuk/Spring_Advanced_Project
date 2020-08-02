package com.ecoverde.estateagency.model.binding;

import com.ecoverde.estateagency.model.view.PropertyViewModel;

import java.util.List;

public class AjaxResponseBody {
    private String message;
    private List<PropertyViewModel> result;

    public AjaxResponseBody() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PropertyViewModel> getResult() {
        return result;
    }

    public void setResult(List<PropertyViewModel> result) {
        this.result = result;
    }
}
