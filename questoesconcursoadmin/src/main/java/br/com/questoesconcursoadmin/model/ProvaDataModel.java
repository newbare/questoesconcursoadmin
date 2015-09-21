package br.com.questoesconcursoadmin.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class ProvaDataModel extends ListDataModel<Prova> implements SelectableDataModel<Prova>{

	
	public ProvaDataModel() {
    }
 
    public ProvaDataModel(List<Prova> data) {
        super(data);
    }
	
	@Override
	public Prova getRowData(String rowKey) {
		List<Prova> provas = (List<Prova>) getWrappedData();
        
        for(Prova prova : provas) {
            if(prova.getId().equals(Integer.parseInt(rowKey)))
                return prova;
        }
         
        return null;
	}

	@Override
	public Object getRowKey(Prova object) {
		return object.getId();
	}
}
