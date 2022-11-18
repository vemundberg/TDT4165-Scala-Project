import exceptions._

class Account(val bank: Bank, initialBalance: Double) {

    class Balance(var amount: Double) {}

    val balance = new Balance(initialBalance)

    // TODO
    // for project task 1.2: implement functions
    // for project task 1.3: change return type and update function bodies
    def withdraw(amount: Double): Either[String, String] = {
         balance.synchronized{
            if (balance.amount - amount < 0){
                Right("Not enough money") //Right is failing 
            }
            else if(amount < 0) {
                Right("Cannot withdraw 0")
            }
            else{
                balance.amount -= amount
                Left("Success!") //Returns success on successful withdraw
            }
         }
    }
    def deposit (amount: Double): Either[String,String] = {
        balance.synchronized{
            if(amount < 0){
                Right("Cannot deposit negative money")
            }
            else{
                balance.amount += amount
                Left("Success!") // Retuns Left on successful deposit
        }   

        }
    }
    def getBalanceAmount: Double = {
        balance.amount 
    }

    def transferTo(account: Account, amount: Double) = {
        bank addTransactionToQueue (this, account, amount)
    }


}
