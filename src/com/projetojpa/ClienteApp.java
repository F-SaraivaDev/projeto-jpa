package com.projetojpa;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ClienteApp {

	public static void main(String[] args) {
		
		try {
			EntityManagerFactory emf =
					Persistence.createEntityManagerFactory("jPAApp");
					EntityManager em = emf.createEntityManager();
					EntityTransaction tx = em.getTransaction();
			int opcao = 0; 	long cpf;
			String nome, email;
			Scanner entrada = new Scanner(System.in);
						
			while(opcao != 6){
				
				System.out.println("\nSistema de Gerenciamento de Clientes");
				System.out.println("====================================");
				System.out.println("Digite [1] para Consultar Todos os Clientes");
				System.out.println("Digite [2] para Consultar um Cliente Específico");
				System.out.println("Digite [3] para Cadastrar um Novo Cliente");
				System.out.println("Digite [4] para Alterar um Cliente");
				System.out.println("Digite [5] para Excluir um Cliente");
				System.out.println("Digite [6] para Sair");
				System.out.println("====================================");
				opcao = entrada.nextInt();
				
				switch(opcao)
				{
					case 1: //Consultar Todos
					{
						System.out.println("[1] Consultar Todos");
						TypedQuery<Cliente> query = em.createQuery("select c from Cliente c", Cliente.class);
						List<Cliente> clientes = query.getResultList();
						clientes.forEach(System.out::println);
						break;
					}
					case 2: //Consultar
					{
						System.out.println("[2] Consultar um Cliente Específico");
						System.out.println("Favor informar o CPF: ");
						cpf =  entrada.nextLong();
					    Cliente cliente = em.find(Cliente.class, cpf);
					    System.out.println(cliente);
						break;						
					}
					case 3: //Cadastrar 
					{
						System.out.println("[3] Cadastrar um Novo Cliente");
						System.out.println("Favor informar o CPF: ");
						cpf = entrada.nextLong();
						entrada.nextLine(); //esvaziar o buffer do teclado
						System.out.println("Favor informar o Nome: ");
						nome = entrada.nextLine();
						System.out.println("Favor informar o Email: ");
						email = entrada.nextLine();	
						Cliente cliente = new Cliente(cpf,nome, email);
						tx.begin();
						em.persist(cliente);
						tx.commit();
						break;					
					}
					case 4: //Alterar
					{
						System.out.println("[4] Alterar um Cliente");
						System.out.println("Favor informar o CPF: ");
						cpf = entrada.nextLong();
						entrada.nextLine(); //esvaziar o buffer do teclado
						System.out.println("Favor informar o Nome: ");
						nome = entrada.nextLine();
						System.out.println("Favor informar o Email: ");
						email = entrada.nextLine();
						Cliente cliente = new Cliente(cpf,nome, email);
					    em.merge(cliente);
					    tx.commit();
						break;
					}
					case 5: //Excluir
					{
						System.out.println("[5] Excluir um Cliente");
						System.out.println("Favor informar o CPF: ");
						cpf = entrada.nextLong();
						Cliente cliente = em.find(Cliente.class, cpf);
						tx.begin();
						em.remove(cliente);
						tx.commit();
						break;						
					}
					case 6: //Sair
					{
						System.out.println("Encerrando o Sistema...");
						em.close();
						System.exit(0);
						break;
					}
				}	
			}
			entrada.close();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}

}


