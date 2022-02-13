import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun rubToKopecks_shouldMultiplyBy100() {
        val amountInRub = 50F

        val result = rubToKopecks(amountInRub = amountInRub)

        assertEquals(5000, result)

    }

    @Test
    fun commissionCalculationForMastercardMaestro_shouldBeNoCommission() {
        val amountInKopecks = 1_000_000
        val amountPreTransferInKopecks = 7400_000

        val result = commissionCalculationForMastercardMaestro(amountInKopecks, amountPreTransferInKopecks)

        assertEquals(0, result)

    }

    @Test
    fun commissionCalculationForMastercardMaestro_shouldBeCommission() {
        val amountInKopecks = 1_000_000
        val amountPreTransferInKopecks = 7600_000

        val result = commissionCalculationForMastercardMaestro(amountInKopecks, amountPreTransferInKopecks)

        assertEquals(8000, result)

    }

    @Test
    fun commissionCalculationForVisaMir_shouldBeMinCommission() {
        val amountInKopecks = 450_000

        val result = commissionCalculationForVisaMir(amountInKopecks)

        assertEquals(3500, result)

    }

    @Test
    fun commissionCalculationForVisaMir_shouldBeCommissionPercentage() {
        val amountInKopecks = 470_000

        val result = commissionCalculationForVisaMir(amountInKopecks)

        assertEquals(3525, result)
    }

    @Test
    fun commissionCalculation_shouldBeCommissionForVkPay() {
        val paymentType = PAYMENT_TYPE_VK
        val amountPreTransferInKopecks = 20_000_000
        val amountInKopecks = 20_000_000

        val result = commissionCalculation(paymentType, amountPreTransferInKopecks, amountInKopecks)

        assertEquals(0, result)
    }

    @Test
    fun commissionCalculation_shouldBeCommissionForMastercardMaestro() {
        val paymentType = PAYMENT_TYPE_MASTERCARD_MAESTRO
        val amountPreTransferInKopecks = 20_000_000
        val amountInKopecks = 20_000_000

        val result = commissionCalculation(paymentType, amountPreTransferInKopecks, amountInKopecks)

        assertEquals(12_2000, result)
    }

    @Test
    fun commissionCalculation_shouldBeCommissionForVisaMir() {
        val paymentType = PAYMENT_TYPE_VISA_MIR
        val amountPreTransferInKopecks = 20_000_000
        val amountInKopecks = 20_000_000

        val result = commissionCalculation(paymentType, amountPreTransferInKopecks, amountInKopecks)

        assertEquals(150_000, result)
    }

    @Test
    fun commissionCalculation_shouldBeNoCommissionWithoutPayMethod() {
        val paymentType = ""
        val amountPreTransferInKopecks = 20_000_000
        val amountInKopecks = 20_000_000

        val result = commissionCalculation(paymentType, amountPreTransferInKopecks, amountInKopecks)

        assertEquals(0, result)
    }
}