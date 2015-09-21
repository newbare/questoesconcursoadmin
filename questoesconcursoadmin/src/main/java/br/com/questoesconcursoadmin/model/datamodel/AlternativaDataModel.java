package br.com.questoesconcursoadmin.model.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.questoesconcursoadmin.model.Alternativa;

public class AlternativaDataModel extends ListDataModel<Alternativa> implements SelectableDataModel<Alternativa>{

	
	public AlternativaDataModel(){
		
	}
	
	public AlternativaDataModel(List<Alternativa> listaAlternativa){
		super(listaAlternativa);
	}
	
	
	@Override
	public Object getRowKey(Alternativa alternativa) {
		return alternativa.getAlternativa();
	}

	@Override
	public Alternativa getRowData(String rowKey) {
		
		List<Alternativa> alternativas = (List<Alternativa>) getWrappedData();
		
		for(Alternativa a : alternativas){
			if(a.getAlternativa().equals(rowKey)){
				return a;
			}
		}
		
		return null;
	}

}
