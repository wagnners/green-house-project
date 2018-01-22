/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.uc;

import br.udesc.greenhouse.modelo.dao.jpa.JPANoticiaDAO;
import br.udesc.greenhouse.modelo.entidade.Noticia;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author ignoi
 */
public class NewClass {
    
//    public static void main(String[] args) {
//        JPANoticiaDAO dao = new JPANoticiaDAO();
//        int i = 0;
//        for (char a = 'a'; a <= 'z'; a++) {
//            Noticia u = new Noticia();
//            u.setCpf(getCPF(Integer.toString((int )Math.ceil(Math.random() * 1000000000))));
//            u.setNome("user" + a);
//            u.setEmail(a + "@email.com");
//            u.setSenha("senha");
//            dao.inserir(u);
//            i++;
//        }
//    }
//    
//    public static String getCPF(String cpf){
//        String resultado = "";
//        resultado += cpf;
//        for (int i = cpf.length(); i < 11; i++) {
//            resultado += "0";
//        }
//        
//        return resultado;
//    }
    
    public static void main(String[] args) {
        JPANoticiaDAO dao = new JPANoticiaDAO();
        List<Noticia> noticias = new ArrayList<>();
        noticias.add(new Noticia(0, "", "Udesc Ibirama apresenta projeto de extensão sobre estudos ambientais para comunidade nesta terça", true, "As ações do projeto de extensão Green House - Laboratório de Estudos Ambientais, do Centro de Educação Superior do Alto Vale do Itajaí (Ceavi), da Universidade do Estado de Santa Catarina (Udesc), serão apresentadas à comunidade acadêmica e externa nesta terça-feira, 14, a partir das 16h30, na casa de enxaimel localizada na sede da unidade, em Ibirama.", Calendar.getInstance().getTime()));
        noticias.add(new Noticia(0, "", "Ações de projeto de extensão da Udesc Ibirama sobre estudos ambientais começarão em junho", true, "Coordenadas pela professora Priscila Natasha Kinas, as atividades envolverão a técnica Dayane Dornelles, o professor Willian Goetten e os seguintes alunos voluntários de Engenharia Sanitária: Rodrigo Porto, Barbara Kayser, Diomar Caetano, Thiago Martins, Felipe Pereira, Jéssica Kisner, Tayla Correa, Ana Luíza Parisotto, Thaís Ferreira, Vitor Pereira,  Mônica Martins e Juçara Keske.", Calendar.getInstance().getTime()));
        for (Noticia noticia : noticias) {
            dao.inserir(noticia);
        }
        
    }
    
}
