package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        
        @Test
        // S3
        public void testImpressionMontantInsuffisant() {
            machine.insertMoney(20);
            assertFalse(machine.printTicket());
        }
        
        @Test
        // S4
        public void testImpressionMontantSuffisant() {
            machine.insertMoney(50);
            assertTrue(machine.printTicket());
        }
        
        @Test
        // S5
        public void testDecrementBalanceOfToicketPrice() {
            machine.insertMoney(50);
            machine.printTicket();
            assertEquals("La balance n'est pas correctement décrémentée", 0, machine.getBalance());
        }
        
        @Test
        // S6
        public void testMAJAmountWhenPrintTicket() {
            machine.insertMoney(50);
            assertEquals("Total incrémenté trop tôt", 0, machine.getTotal());
            machine.printTicket();
            assertEquals("Total pas incrémenté à l'achat du ticket", 50, machine.getTotal());
        }
        
        @Test
        // S7 & S8
        public void testRefund() {
            machine.insertMoney(30);
            assertEquals("Ne rends pas correctement la monaie et ne réinitialise pas la balance", 0, machine.refund());
        }
        
        @Test (expected = IllegalArgumentException.class)
        // S9
        public void testInterNegativeAmount() {
            machine.insertMoney(-50);
        }
        
        @Test (expected = IllegalArgumentException.class)
        // S10
        public void testTicketPriceIsNotNegative() {
            new TicketMachine(-50);
        }
        
}
