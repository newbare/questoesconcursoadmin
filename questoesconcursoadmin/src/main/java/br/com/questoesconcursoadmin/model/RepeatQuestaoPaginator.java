package br.com.questoesconcursoadmin.model;

import java.util.ArrayList;
import java.util.List;

import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.remote.AlternativaRemote;

public class RepeatQuestaoPaginator {
	
	AlternativaRemote alternativaRemote;

    private static final int DEFAULT_RECORDS_NUMBER = 10;
    private static final int DEFAULT_PAGE_INDEX = 1;

    private int records;
    private int recordsTotal;
    private int pageIndex;
    private int pages;
    private List<Questao> origModel;
    private List<Questao> model;
    
    public RepeatQuestaoPaginator(){
    	
    }

    public RepeatQuestaoPaginator(List<Questao> model, AlternativaRemote ar) {
        this.origModel = model;
        this.records = DEFAULT_RECORDS_NUMBER;
        this.pageIndex = DEFAULT_PAGE_INDEX;        
        this.recordsTotal = model.size();
        this.alternativaRemote = ar;

        if (records > 0) {
            pages = records <= 0 ? 1 : recordsTotal / records;

            if (recordsTotal % records > 0) {
                pages++;
            }

            if (pages == 0) {
                pages = 1;
            }
        } else {
            records = 1;
            pages = 1;
        }

        updateModel();
    }

    public void updateModel() {
        int fromIndex = getFirst();
        int toIndex = getFirst() + records;

        if(toIndex > this.recordsTotal) {
            toIndex = this.recordsTotal;
        }

        this.model = origModel.subList(fromIndex, toIndex);
        
        if(model != null && model.size() > 0){
	        for(int i = 0; i < model.size(); i++){
				model.get(i).setListaAlternativa(carregaAlternativasByQuestao(model.get(i)));
			}
        }
    }
    
    /**
	 * Método que recupera todas as bancas e converte para uma lista de SelectItem
	 */
	List<Alternativa> carregaAlternativasByQuestao(Questao questao){
		try {
			Alternativa alternativa = new Alternativa();
			alternativa.setQuestao(questao);
			List<Alternativa> lista = alternativaRemote.recuperarPorEntidade(alternativa);
			if(lista != null){
				return lista;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
		return new ArrayList<Alternativa>();
	}

    public void next() {
        if(this.pageIndex < pages) {
            this.pageIndex++;
        }

        updateModel();
    }

    public void prev() {
        if(this.pageIndex > 1) {
            this.pageIndex--;
        }

        updateModel();
    }   

    public int getRecords() {
        return records;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPages() {
        return pages;
    }

    public int getFirst() {
        return (pageIndex * records) - records;
    }

    public List<Questao> getModel() {
        return model;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

}