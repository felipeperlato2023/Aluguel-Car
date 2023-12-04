

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
	
	public static void main(String[] args) {
		
		//ja com a branch nova
		
		Scanner ler = new Scanner(System.in);

        Locale.setDefault(Locale.US);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Entre com os dados do aluguel");
        System.out.println("Modelo do carro");
        String carModel = ler.nextLine();

        // Criação do objeto Vehicle
        Vehicle v = new Vehicle(carModel);

        System.out.println("Retirada (dd/mm/yyyy hh:mm)");
        String inputData = ler.nextLine();
        LocalDateTime start = LocalDateTime.parse(inputData, fmt);

        System.out.println("Entrega (dd/mm/yyyy hh:mm)");
        LocalDateTime finish = LocalDateTime.parse(ler.nextLine(), fmt);

        System.out.println("Entre com o preço por hora ");
        Double precoHora = ler.nextDouble();

        System.out.println("Entre com o preço por dia");
        Double precoDia = ler.nextDouble();
        
        RentalService rentalservice = new RentalService(precoHora,precoDia, new BrazilTaxService());

        CarRental cr = new CarRental(start, finish,v);

        System.out.println(cr);
		
		
        rentalservice.processInvoice(cr);
        
        System.out.println("FATURA: ");
        System.out.println("PAGAMENTO BASICO: " + cr.getInvoice().getBasicPayment());
        System.out.println("IMPOSTO: " + cr.getInvoice().getTax());
        System.out.println("PAGAMENTO TOTAL: " + cr.getInvoice().getTotalPayment());
        

        ler.close();
    }
}

