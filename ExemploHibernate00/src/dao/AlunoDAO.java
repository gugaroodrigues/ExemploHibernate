/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Aluno;
import database.Conexao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alunos
 */
public class AlunoDAO {

    public List<Aluno> obterTodos() {
        List<Aluno> alunos = new ArrayList<>();
        Conexao conexao = new Conexao();
        if (conexao.conectar()) {
            alunos = conexao.session.createQuery("from Aluno").list();

        }
        return alunos;
    }

    public int inserir(Aluno aluno) {
        Conexao conexao = new Conexao();
        if (conexao.conectar()) {
            conexao.session.save(aluno);
            conexao.transaction.commit();
            conexao.session.close();
            return aluno.getId();
        }
        return -1;
    }

    public boolean alterar(Aluno aluno) {
        Conexao conexao = new Conexao();
        if(conexao.conectar()){
            conexao.session.update(aluno);
            conexao.transaction.commit();
            conexao.session.close();
            return true;
        }
        return false;
    }

    public boolean excluir() {
        return false;

    }

    public boolean excluir(int id) {
        Conexao conexao = new Conexao();
        if (conexao.conectar()) {
            Aluno aluno = conexao.session.get(Aluno.class, id);
            conexao.session.delete(aluno);
            conexao.transaction.commit();
            conexao.session.close();
            return true;
        }
        return false;
    }

    public Aluno obterPeloId(int id) {
        Aluno aluno = null;
        Conexao conexao = new Conexao();
        if (conexao.conectar()) {
            aluno = conexao.session.get(Aluno.class, id);
            conexao.session.update(aluno);
            conexao.transaction.commit();
            conexao.session.close();
        }
        return aluno;

    }
}
