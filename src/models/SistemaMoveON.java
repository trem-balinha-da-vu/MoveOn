package models;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import enums.StatusViagem;
import org.apache.poi.ss.usermodel.*; // Para manipular planilhas Excel (Workbook, Sheet, Row, Cell)
import org.apache.poi.xssf.usermodel.XSSFWorkbook; // Para trabalhar com arquivos Excel no formato .xlsx

import java.io.FileOutputStream; // Para escrever no arquivo Excel


public class SistemaMoveON {
    private static final String ARQUIVO_BANCO = "src\\banco.xlsx";

    // Mock de motoristas para simular motoristas disponíveis
    public Motorista criaMockMotorista() {
        List<Motorista> motoristasMock = Arrays.asList(
                new Motorista(1L, "João Silva", "joao.silva@motorista.com", "99999-1111", "ABC1234"),
                new Motorista(2L, "Maria Santos", "maria.santos@motorista.com", "99999-2222", "XYZ5678"),
                new Motorista(3L, "Pedro Oliveira", "pedro.oliveira@motorista.com", "99999-3333", "QWE8901")
        );

        // Simular que escolhe um motorista aleatoriamente
        Random random = new Random();
        return motoristasMock.get(random.nextInt(motoristasMock.size()));
    }

    // Função para escolher um motorista aleatório do banco
    public Motorista escolheMotoristaAleatorio() {
        List<Motorista> motoristasMock = recuperaMotoristas();

        // Simular que escolhe um motorista aleatoriamente
        Random random = new Random();
        return motoristasMock.get(random.nextInt(motoristasMock.size()));
    }

    public List<Viagem> recuperaHistorico(Cliente cliente) {
        if (cliente == null) {
            return Collections.emptyList(); // Retorna uma lista vazia se o cliente for nulo
        }

        try (FileInputStream file = new FileInputStream(ARQUIVO_BANCO);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet viagensSheet = workbook.getSheet("Viagens");
            List<Viagem> historico = new ArrayList<>();

            for (Row row : viagensSheet) {
                if (row.getRowNum() == 0) continue; // Ignorar cabeçalho

                Long idCliente = (long) row.getCell(2).getNumericCellValue();

                // Verifica se o ID do cliente corresponde ao cliente fornecido
                if (idCliente.equals(cliente.id)) {
                    Long idViagem = (long) row.getCell(0).getNumericCellValue();
                    String codigo = row.getCell(1).getStringCellValue();
                    String origem = row.getCell(3).getStringCellValue();
                    String destino = row.getCell(4).getStringCellValue();

                    String statusString = row.getCell(5).getStringCellValue();
                    StatusViagem status = StatusViagem.PENDENTE; // Valor padrão
                    try {
                        status = StatusViagem.valueOf(statusString.toUpperCase().replace(" ", "_"));
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }

                    double valor = row.getCell(6).getNumericCellValue();

                    Long idMotorista = (long) row.getCell(7).getNumericCellValue();
                    Motorista motorista = retornaMotorista(idMotorista); // Busca o motorista associado

                    // Criação da viagem
                    Viagem viagem = new Viagem(idViagem, codigo, cliente, motorista, origem, destino, status, valor);
                    historico.add(viagem);
                }
            }

            return historico;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList(); // Retorna uma lista vazia caso ocorra algum erro
    }


    public Motorista retornaMotorista(Long ID)
    {
        List<Motorista> lst = recuperaMotoristas();
        Iterator it = lst.iterator();

        while(it.hasNext())
        {
            Motorista motorista = (Motorista) it.next(); // Obtém o próximo cliente
            if (motorista.id.equals(ID)) { // Verifica se o ID do cliente é igual ao ID passado
                return motorista; // Retorna o cliente encontrado
            }
        }

        return null;
    }

    // Recupera lista de motoristas
    public List<Motorista> recuperaMotoristas()
    {
        List<Motorista> listaMotoristas = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(ARQUIVO_BANCO);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet motoristasSheet = workbook.getSheet("Motorista");

            // Carregar os clientes
            for (Row row : motoristasSheet) {
                if (row.getRowNum() == 0) continue; // Ignorar cabeçalho

                Long idMotorista = (long) row.getCell(0).getNumericCellValue();
                String nome = row.getCell(1).getStringCellValue();
                String email = row.getCell(2).getStringCellValue();
                String telefone = row.getCell(3).getStringCellValue();
                String placa = row.getCell(4).getStringCellValue();

                Motorista motorista = new Motorista(idMotorista, nome, email, telefone, placa);
                listaMotoristas.add(motorista);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaMotoristas;
    }

    // Recupera a lista de clientes e associa o histórico de viagens
    public List<Cliente> recuperaClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        Map<Long, List<Viagem>> historicoViagensPorCliente = new HashMap<>();

        try (FileInputStream file = new FileInputStream(ARQUIVO_BANCO);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet usuariosSheet = workbook.getSheet("Usuários");
            Sheet viagensSheet = workbook.getSheet("Viagens");

            // Carregar viagens e organizá-las por cliente
            for (Row row : viagensSheet) {
                if (row.getRowNum() == 0) continue; // Ignorar cabeçalho

                Long idViagem = (long) row.getCell(0).getNumericCellValue();
                String codigo = row.getCell(1).getStringCellValue();
                Long idCliente = (long) row.getCell(2).getNumericCellValue();
                String origem = row.getCell(3).getStringCellValue();
                String destino = row.getCell(4).getStringCellValue();

                String statusString = row.getCell(5).getStringCellValue();

                StatusViagem status = StatusViagem.PENDENTE;  // Valor padrão caso o valor não seja reconhecido
                try {
                    status = StatusViagem.valueOf(statusString.toUpperCase().replace(" ", "_"));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }

                double valor = row.getCell(6).getNumericCellValue();
                Long idMotorista = (long) row.getCell(7).getNumericCellValue();

                Viagem viagem = new Viagem(idViagem, codigo, null, null, origem, destino, status, valor);

                historicoViagensPorCliente.computeIfAbsent(idCliente, k -> new ArrayList<>()).add(viagem);
            }

            // Carregar os clientes
            for (Row row : usuariosSheet) {
                if (row.getRowNum() == 0) continue; // Ignorar cabeçalho

                Long idCliente = (long) row.getCell(0).getNumericCellValue();
                String tipo = row.getCell(1).getStringCellValue();

                if (!"Cliente".equals(tipo)) continue;

                String nome = row.getCell(2).getStringCellValue();
                String email = row.getCell(3).getStringCellValue();
                String telefone = row.getCell(4).getStringCellValue();

                List<Viagem> historicoViagens = historicoViagensPorCliente.getOrDefault(idCliente, new ArrayList<>());
                Cliente cliente = new Cliente(idCliente, nome, email, telefone, historicoViagens);
                listaClientes.add(cliente);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaClientes;
    }

    public Cliente retornaCliente(Long ID)
    {
        List<Cliente> lst = recuperaClientes();
        Iterator it = lst.iterator();

        while(it.hasNext())
        {
            Cliente cliente = (Cliente) it.next();
            if (cliente.id.equals(ID)) { // Verifica se o ID do cliente é igual ao ID passado
                return cliente; // Retorna o cliente encontrado
            }
        }

        return null;
    }


    public void salvarViagem(Cliente cliente, Motorista motorista, String codigo, String origem,
                            String destino, Double valor, StatusViagem status)
    {
        try (FileInputStream fis = new FileInputStream(ARQUIVO_BANCO);
             Workbook workbook = new XSSFWorkbook(fis);
             FileOutputStream fos = new FileOutputStream(ARQUIVO_BANCO)) {

            Sheet sheet = workbook.getSheet("Viagens");
            int lastRow = sheet.getLastRowNum();

            Row newRow = sheet.createRow(lastRow + 1);
            newRow.createCell(0).setCellValue(lastRow + 1); // ID
            newRow.createCell(1).setCellValue(codigo); // codigo da corrida
            newRow.createCell(2).setCellValue(cliente.id); // Id do cliente
            newRow.createCell(3).setCellValue(origem); // Origem
            newRow.createCell(4).setCellValue(destino); // Destino
            newRow.createCell(5).setCellValue(status.getStatusDaViagem()); // Status
            newRow.createCell(6).setCellValue(valor); // Preco
            newRow.createCell(7).setCellValue(motorista.id);

            workbook.write(fos); // Salva as alterações no arquivo
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void salvarUsuario(String tipo, String nome, String email, String telefone, String senha, String placa) {
        try (FileInputStream fis = new FileInputStream(ARQUIVO_BANCO);
             Workbook workbook = new XSSFWorkbook(fis);
             FileOutputStream fos = new FileOutputStream(ARQUIVO_BANCO)) {

            Sheet sheet = workbook.getSheet("Usuários");
            int lastRow = sheet.getLastRowNum();

            Row newRow = sheet.createRow(lastRow + 1);
            newRow.createCell(0).setCellValue(lastRow + 1); // ID
            newRow.createCell(1).setCellValue(tipo); // Tipo
            newRow.createCell(2).setCellValue(nome); // Nome
            newRow.createCell(3).setCellValue(email); // Email
            newRow.createCell(4).setCellValue(telefone); // Telefone
            newRow.createCell(5).setCellValue(senha); // Senha
            newRow.createCell(6).setCellValue(placa != null ? placa : ""); // Placa

            workbook.write(fos); // Salva as alterações no arquivo
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Metodo para verificar se o usuário existe no arquivo
    public Cliente recuperarUsuario(String email, String senha) {
        try (FileInputStream file = new FileInputStream(ARQUIVO_BANCO);
             Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheet("Usuários");

            for (Row row : sheet) {
                Cell emailCell = row.getCell(3);
                Cell senhaCell = row.getCell(5);
                Cell idCell = row.getCell(0);
                System.out.println(idCell.getCellType());

                if (emailCell != null && senhaCell != null) {
                    String emailValue = emailCell.getStringCellValue();
                    String senhaValue = senhaCell.getStringCellValue();

                    // Verifica se a célula de ID contém um valor numérico válido
                    Long idValue = null;
                    if (idCell.getCellType() == CellType.NUMERIC) {
                        idValue = (long) idCell.getNumericCellValue();
                    } else if (idCell.getCellType() == CellType.STRING) {
                        try {
                            idValue = Long.parseLong(idCell.getStringCellValue());
                        } catch (NumberFormatException e) {
                            continue; // Pule para a próxima linha
                        }
                    }

                    if (emailValue.equals(email) && senhaValue.equals(senha)) {
                        return retornaCliente(idValue); // Encontrou o usuário
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Não encontrou o usuário
    }




}
