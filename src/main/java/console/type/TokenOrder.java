package console.type;

public sealed interface TokenOrder 
                        permits TokenOrder.FirstOrSecond,
                                TokenOrder.OptativeFirstOrSecond,
                                TokenOrder.Required,
                                TokenOrder.Optative,
                                TokenOrder.OneOrMore {
    record         FirstOrSecond(TokenType token) implements TokenOrder {}
    record OptativeFirstOrSecond(TokenType token) implements TokenOrder {}
    record              Required(TokenType token) implements TokenOrder {}
    record              Optative(TokenType token) implements TokenOrder {}
    record             OneOrMore(TokenType token) implements TokenOrder {}
}
