package by.minsk.kes.coinmarket.transform.parser.exception;

public class CoinMarketException extends RuntimeException {

    public CoinMarketException(final String message) {
        super(message);
    }

    public CoinMarketException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
