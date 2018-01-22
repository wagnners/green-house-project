/*
 UC007 Formulários de mensagem da página inicialo.
 https://www.google.com/settings/u/1/security/lesssecureapps
 Para este formulario funcionar deve ser acessado o link acima para ativar as lesssecureapps;
 */
package br.udesc.greenhouse.uc;

import br.udesc.greenhouse.modelo.dao.core.ConfiguracaoDAO;
import br.udesc.greenhouse.modelo.dao.core.FactoryDAO;
import br.udesc.greenhouse.modelo.entidade.Configuracao;

/**
 *
 * @author sila
 */
public class ConfiguracaoUC {

    private Configuracao configuracao;
    private ConfiguracaoDAO cdao;

    public ConfiguracaoUC() {
        cdao = FactoryDAO.getFactoryDAO().getConfiguracaoDAO();
        configuracao = cdao.listar().get(0);
    }

    public Configuracao getConfiguracao() {
        return configuracao;
    }

    public void salvarConfiguracao(Configuracao configuracao) {
        cdao.editar(configuracao);
    }

}
