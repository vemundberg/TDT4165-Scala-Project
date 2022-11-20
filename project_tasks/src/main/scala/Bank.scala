class Bank(val allowedAttempts: Integer = 3) {

    private val transactionsQueue: TransactionQueue = new TransactionQueue()
    private val processedTransactions: TransactionQueue = new TransactionQueue()

    def addTransactionToQueue(from: Account, to: Account, amount: Double): Unit = {
        // create a new transaction object and put it in the queue
        transactionsQueue push new Transaction(transactionsQueue, processedTransactions, from, to, amount, allowedAttempts)
        // spawn a thread that calls processTransactions 
        val thread: Thread = new Thread {
            override def run() = processTransactions
        }
        thread.start
    }

    private def processTransactions: Unit = {
        // Function that pops a transaction from the queue
        val transaction: Transaction = transactionsQueue.pop
        transaction.run()
        if (transaction synchronized { transaction.status == TransactionStatus.PENDING }) {
            // and spawns a thread to execute the transaction.
            transactionsQueue push transaction 
            processTransactions
        } else {
            // Finally do the appropriate thing, depending on whether
            // the transaction succeeded or not
            processedTransactions push transaction
        }
    }
                                                
                                                
                                                

    def addAccount(initialBalance: Double): Account = {
        new Account(this, initialBalance)
    }

    def getProcessedTransactionsAsList: List[Transaction] = {
        processedTransactions.iterator.toList
    }

}
