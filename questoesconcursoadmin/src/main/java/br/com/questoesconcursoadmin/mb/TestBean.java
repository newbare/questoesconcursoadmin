package br.com.questoesconcursoadmin.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.questoesconcursoadmin.model.RepeatQuestaoPaginator;

@ManagedBean
@SessionScoped
public class TestBean {

	private List	<String> list;
    private RepeatQuestaoPaginator paginator;

    @PostConstruct
    public void init() {
        this.list = new ArrayList<String>();
        this.list.add("Item 1");
        this.list.add("Item 2");
        this.list.add("Item 3");
        this.list.add("Item 4");
        this.list.add("Item 5");
        this.list.add("Item 6");
        this.list.add("Item 7");
        this.list.add("Item 8");
        this.list.add("Item 9");
        this.list.add("Item 10");
        this.list.add("Item 11");
//        paginator = new RepeatQuestaoPaginator(this.list);
    }

    public RepeatQuestaoPaginator getPaginator() {
        return paginator;
    }

}
