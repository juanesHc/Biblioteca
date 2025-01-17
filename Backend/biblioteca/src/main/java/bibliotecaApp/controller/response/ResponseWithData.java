package bibliotecaApp.controller.response;

import crosscutting.helpers.ObjectHelper;

import java.util.ArrayList;
import java.util.List;

public abstract
class  ResponseWithData<T> extends Response {

    private List<T> data=new ArrayList<>();

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = ObjectHelper.getDefault(data,this.data);
    }
}
