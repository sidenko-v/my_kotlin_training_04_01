const val PAYMENT_TYPE_VK = "Vk Pay"
const val PAYMENT_TYPE_MASTERCARD_MAESTRO = "Mastercard и Maestro"
const val PAYMENT_TYPE_VISA_MIR = "Visa и Мир"

fun main() {

    val paymentType = PAYMENT_TYPE_VISA_MIR
    val amountPreTransferInRub = 10000F
    val amountInRub = 40000F
    val commission = commissionCalculation(paymentType, rubToKopecks(amountPreTransferInRub), rubToKopecks(amountInRub))
    println(commission)

}

fun rubToKopecks(amountInRub: Float): Int {
    val amountInKopecks = (amountInRub * 100).toInt()
    return amountInKopecks
}

fun commissionCalculation(paymentType: String, amountPreTransferInKopecks: Int, amountInKopecks: Int): Int {

    var commission: Int = 0

    when (paymentType) {
        PAYMENT_TYPE_VK -> commission = 0
        PAYMENT_TYPE_MASTERCARD_MAESTRO -> commission =
            commissionCalculationForMastercardMaestro(amountInKopecks, amountPreTransferInKopecks)
        PAYMENT_TYPE_VISA_MIR -> commission = commissionCalculationForVisaMir(amountInKopecks)
    }

    return commission
}

fun commissionCalculationForMastercardMaestro(amountInKopecks: Int, amountPreTransferInKopecks: Int): Int {

    var commission: Int = 0

    val maxPreAmount = rubToKopecks(75000F)
    val comPercentage = 0.6F
    val comPart = comPercentage / 100

    when (amountPreTransferInKopecks) {
        in 0..maxPreAmount -> commission = 0
        else -> commission = (amountInKopecks * comPart + rubToKopecks(20F)).toInt()
    }
    return commission
}

fun commissionCalculationForVisaMir(amountInKopecks: Int): Int {
    var commission: Int = 0

    val minCommission: Int = rubToKopecks(35F)
    val comPercentage = 0.75F
    val comPart = comPercentage / 100
    val initCommission = (amountInKopecks * comPart).toInt()

    when (initCommission) {
        in 0..minCommission -> commission = minCommission
        else -> commission = initCommission
    }

    return commission

}
