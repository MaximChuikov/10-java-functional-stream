package com.example.task01;

import java.io.IOException;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public class Task01Main {
    public static void main(String[] args) throws IOException {

        // TODO С корректно реализованным методом ternaryOperator должен компилироваться и успешно работать следующий код:


        Predicate<Object> condition = Objects::isNull;
        Function<Object, Integer> ifTrue = obj -> 0;
        Function<CharSequence, Integer> ifFalse = CharSequence::length;
        Function<String, Integer> safeStringLength = ternaryOperator(condition, ifTrue, ifFalse);

        BiFunction<String, Integer, String> cutString = biTernaryOperator(
                (t, u) -> t.length() > u,
                (t, u) -> t.substring(0, u),
                (t, u) -> t.toLowerCase()
        );

        BiPredicate<CharSequence, Integer> condition2 = (t, u) -> t.length() > u;
        BiFunction<CharSequence, Integer, String> ifTrue2 = (t, u) -> t.subSequence(0, u).toString();
        BiFunction<Object, Integer, String> ifFalse2 = (t, u) -> t.toString() + " плюс " + u;

        BiFunction<String, Integer, String> smth = biTernaryOperator(condition2, ifTrue2, ifFalse2);

        System.out.println(cutString.apply("HeLLo", 10));
        System.out.println(cutString.apply("Hello world", 7));
    }

    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse)
    {
        if (condition == null || ifTrue == null || ifFalse == null)
            throw new NullPointerException();
        return t -> condition.test(t) ? ifTrue.apply(t) : ifFalse.apply(t);
    }

    public static <T, U, R> BiFunction<T, U, R> biTernaryOperator(
            BiPredicate<? super T,? super U> condition,
            BiFunction<? super T,? super U,? extends R> ifTrue,
            BiFunction<? super T,? super U,? extends R> ifFalse
    )
    {
        if (condition == null || ifTrue == null || ifFalse == null)
            throw new NullPointerException();
        return (t, u) -> condition.test(t, u) ? ifTrue.apply(t, u) : ifFalse.apply(t, u);
    }
}
