package com.projetojpa;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class PagamentoApp {

	public static void main(String[] args) {
		try
		{
			EntityManagerFactory emf =
					Persistence.createEntityManagerFactory("jPAApp");
					EntityManager em = emf.createEntityManager();
					EntityTransaction tx = em.getTransaction();
					
			Scanner entrada = new Scanner(System.in);
			int opcao = 0; 	long cpf, cdcurso;
			String datainscricao;
			
			while(opcao != 6)
			{
				System.out.println("\nSistema de Gerenciamento de Pagamentos");
				System.out.println("====================================");
				System.out.println("Digite [1] para Consultar Todos os Pagamentos");
				System.out.println("Digite [2] para Consultar um Pagamento Específico");
				System.out.println("Digite [3] para Cadastrar um Novo Pagamento");
				System.out.println("Digite [4] para Alterar um Pagamento");
				System.out.println("Digite [5] para Excluir um Pagamento");
				System.out.println("Digite [6] para Sair");
				System.out.println("====================================");
				opcao = entrada.nextInt();
				
				switch(opcao)
				{
					case 1: //Consultar Todos
					{
						System.out.println("[1] Consultar Todos");
						TypedQuery<Pagamento> query = em.createQuery("select c from Pagamento c", Pagamento.class);
						List<Pagamento> pagamentos = query.getResultList();
						pagamentos.forEach(System.out::println);
						break;
					}
					case 2: //Consultar
					{
						System.out.println("[2] Consultar um Pagamento Específico");
						System.out.println("Favor informar o CPF: ");
						cpf = entrada.nextLong();
						System.out.println("Favor informar o CDCURSO: ");
						cdcurso = entrada.nextLong();
						PagamentoId pgtoid = new PagamentoId(cpf, cdcurso);
						Pagamento pagamento = em.find(Pagamento.class, pgtoid);
					    System.out.println(pagamento);
						break;						
					}
					case 3: //Cadastrar 
					{
						System.out.println("[3] Cadastrar um Novo Pagamento");
						System.out.println("Favor informar o CPF: ");
						cpf = entrada.nextLong();
						System.out.println("Favor informar o CDCURSO: ");
						cdcurso = entrada.nextLong();
						PagamentoId pgtoid = new PagamentoId(cpf, cdcurso);
						entrada.nextLine(); //esvaziar o buffer do teclado
						System.out.println("Favor informar a DATA DE INSCRICAO: ");
						datainscricao = entrada.nextLine();
						Pagamento pagamento = new Pagamento(pgtoid,datainscricao);
						tx.begin();
						em.persist(pagamento);
						tx.commit();
						break;					
					}
					case 4: //Alterar
					{
						System.out.println("[3] Cadastrar um Novo Pagamento");
						System.out.println("Favor informar o CPF: ");
						cpf = entrada.nextLong();
						System.out.println("Favor informar o CDCURSO: ");
						cdcurso = entrada.nextLong();
						PagamentoId pgtoid = new PagamentoId(cpf, cdcurso);
						entrada.nextLine(); //esvaziar o buffer do teclado
						System.out.println("Favor informar a DATA DE INSCRICAO: ");
						datainscricao = entrada.nextLine();
						Pagamento pagamento = new Pagamento(pgtoid,datainscricao);
						em.merge(pagamento);
					    tx.commit();
						break;
					}
					case 5: //Excluir
					{
						System.out.println("[2] Consultar um Pagamento Específico");
						System.out.println("Favor informar o CPF: ");
						cpf = entrada.nextLong();
						System.out.println("Favor informar o CDCURSO: ");
						cdcurso = entrada.nextLong();
						PagamentoId pgtoid = new PagamentoId(cpf, cdcurso);
						Pagamento pagamento = em.find(Pagamento.class, pgtoid);
						tx.begin();
						em.remove(pagamento);
						tx.commit();
						break;						
					}
					case 6: //Sair
					{
						System.out.println("Encerrando o Sistema...");
						System.exit(0);
						break;
					}
				}	
			}
			entrada.close();			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
