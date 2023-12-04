import java.time.Duration;

public class RentalService {

	private Double pricePerHour;

	private Double pricePerDay;
	
	BrazilTaxService taxService;

	public Double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}


	public RentalService(Double pricePerHour, Double pricePerDay, BrazilTaxService taxService) {

		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService	= taxService;
	}
	
	public void processInvoice(CarRental carrental)
	{
		
		Double minutes = (double) Duration.between(carrental.getStart(), carrental.getFinish()).toMinutes();
		double hours = minutes / 60.0;
		
		double basicPayment;
		if(hours <= 12.0)
		{
			basicPayment = pricePerHour * Math.ceil(hours);
		}
		else
		{
			basicPayment = pricePerDay * Math.ceil(hours/24.0);
		}
		
		double tax = taxService.tax(basicPayment);
		
		carrental.setInvoice(new Invoice(basicPayment, tax));
	}
	

}
