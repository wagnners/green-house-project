/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.bean;

import br.udesc.greenhouse.modelo.dao.core.FactoryDAO;
import br.udesc.greenhouse.modelo.entidade.Imagem;
import br.udesc.greenhouse.modelo.entidade.Oficina;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author ignoi
 */
@ManagedBean
@SessionScoped
public class ImageStreamer {

    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String oficinaId = context.getExternalContext().getRequestParameterMap().get("imageid");
            Oficina oficina = FactoryDAO.getFactoryDAO().getOficinaDAO().pesquisar(Long.valueOf(oficinaId));
            return new DefaultStreamedContent(new ByteArrayInputStream(oficina.getFotoDestaque()));
        }
    }

    public StreamedContent getImg() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String oficinaId = context.getExternalContext().getRequestParameterMap().get("imageid");
            Imagem imagem = FactoryDAO.getFactoryDAO().getImagemDAO().pesquisar(Long.valueOf(oficinaId));
            return new DefaultStreamedContent(new ByteArrayInputStream(imagem.getData()));
        }
    }

}
