package stream_api;

import java.util.*;
import java.util.stream.Collectors;

public class ExerciciosStreamAPI {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 5, 4, 3);

        // Desafio 1
        List<Integer> numerosOdernados = numeros.stream()
                .sorted() // ordena os números em ordem crescente
                .toList();

        numerosOdernados.forEach(System.out::println);


        // Desafio 2
        int somaDosPares = numeros.stream()
                .filter(num -> num % 2 == 0) // Filtra os números pares
                .mapToInt(Integer::intValue)  // Converte para um IntStream
                .sum(); // Soma os números pares

        System.out.println("A soma dos números pares é: " + somaDosPares);

        // Desafio 3
        boolean todosPositivos = numeros.stream()
                .allMatch(num -> num > 0);  // Verifica se todos os números são positivos

        System.out.println(todosPositivos ? "Todos os números são positivos."
                : "Pelo menos um número não é positivo.");

        // Desafio 4
        List<Integer> numerosPares = numeros.stream()
                .filter(num -> num % 2 == 0)
                .toList();

        numerosPares.forEach(System.out::println);

        // Desafio 5
        OptionalDouble mediaMaioresQue5 = numeros.stream()
                .filter(num -> num > 5)           // Filtra os números maiores que 5
                .mapToDouble(Integer::doubleValue)  // Converte para um DoubleStream
                .average();                        // Calcula a média

        if (mediaMaioresQue5.isPresent()) {
            System.out.println("Média dos números maiores que 5: " + mediaMaioresQue5.getAsDouble());
        } else {
            System.out.println("Nenhum número é maior que 5.");
        }

        // Desafio 6
        boolean contemMaiorQue10 = numeros.stream()
                .anyMatch(num -> num > 10);  // Verifica se algum número é maior que 10

        if (contemMaiorQue10) {
            System.out.println("A lista contém pelo menos um número maior que 10.");
        } else {
            System.out.println("A lista não contém números maiores que 10.");
        }

        // Desafio 7
        // Ordena a lista em ordem decrescente e pega o segundo elemento (índice 1)
        numeros.stream()
                .distinct()  // Remove números duplicados
                .sorted(Comparator.reverseOrder())  // Ordena em ordem decrescente
                .skip(1)  // Pula o primeiro elemento (maior número)
                .findFirst()  // Encontra o segundo maior
                .ifPresentOrElse(
                        secondLargest -> System.out.println("O segundo maior número da lista é: " + secondLargest),
                        () -> System.out.println("Não há um segundo maior número na lista.")
                );

        // Desafio 9
        boolean saoDistintos = numeros.stream()
                .distinct() // Remove números duplicados
                .count() == numeros.size();

        if (saoDistintos) {
            System.out.println("Todos os números na lista são distintos.");
        } else {
            System.out.println("Pelo menos um número se repete na lista.");
        }

        // Desafio 10
        Map<Boolean, List<Integer>> grupos = numeros.stream()
                .filter(num -> num % 2 == 1)  // Filtra números ímpares
                .filter(num -> num % 3 == 0 || num % 5 == 0)  // Filtra múltiplos de 3 ou 5
                .collect(Collectors.partitioningBy(num -> num % 3 == 0, Collectors.toList()));

        List<Integer> multiplosDe3 = grupos.get(true);
        List<Integer> multiplosDe5 = grupos.get(false);

        System.out.println("Valores ímpares múltiplos de 3: " + multiplosDe3);
        System.out.println("Valores ímpares múltiplos de 5: " + multiplosDe5);

        // Desafio 11
        int somaDosQuadrados = numeros.stream()
                .mapToInt(num -> num * num)  // Eleva ao quadrado
                .sum();  // Soma os quadrados

        System.out.println("A soma dos quadrados de todos os números é: " + somaDosQuadrados);

        // Desafio 12
        int produtoDosNumeros = numeros.stream()
                .reduce(1, (a, b) -> a * b);  // Multiplica os números

        System.out.println("O produto de todos os números é: " + produtoDosNumeros);

        // Desafio 13
        List<Integer> numerosNoIntervalo = numeros.stream()
                .filter(num -> num >= 5 && num <= 10)  // Filtra os números no intervalo
                .toList();

        System.out.println("Números no intervalo [5, 10]: " + numerosNoIntervalo);

        // Desafio 15
        boolean contemNegativo = numeros.stream()
                .anyMatch(num -> num < 0);  // Verifica se algum número é negativo

        if (contemNegativo) {
            System.out.println("A lista contém pelo menos um número negativo.");
        } else {
            System.out.println("A lista não contém números negativos.");
        }

        // Desafio 16
        Map<Boolean, List<Integer>> numerosAgrupados = numeros.stream()
                .collect(Collectors.partitioningBy(num -> num % 2 == 0));

        List<Integer> pares = numerosAgrupados.get(true);
        List<Integer> impares = numerosAgrupados.get(false);

        System.out.println("Números pares: " + pares);
        System.out.println("Números ímpares: " + impares);

        // Desafio 18
        boolean todosIguais = numeros.stream()
                .allMatch(num -> num.equals(numeros.get(0)));

        if (todosIguais) {
            System.out.println("Todos os números na lista são iguais.");
        } else {
            System.out.println("Pelo menos um número é diferente na lista.");
        }

        // Desafio 19
        int somaDosDivisiveisPor3e5 = numeros.stream()
                .filter(num -> num % 3 == 0 && num % 5 == 0)
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("A soma dos números divisíveis por 3 e 5 é: " + somaDosDivisiveisPor3e5);

    }
}

// Desafio 8
class Desafio8 {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 5, 4, 3);

        int somaDosDigitos = numeros.stream()
                .mapToInt(Desafio8::somaDosDigitos)
                .sum();

        System.out.println("A soma dos dígitos de todos os números é: " + somaDosDigitos);
    }

    public static int somaDosDigitos(int numero) {
        int soma = 0;
        while (numero != 0) {
            soma += numero % 10; // Obtém o último dígito e adiciona à soma
            numero /= 10; // Remove o último dígito
        }
        return soma;
    }
}
// Desafio 14
class Desafio14 {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 5, 4, 3);

        int maiorPrimo = numeros.stream()
                .filter(Desafio14::isPrimo)  // Filtra números primos
                .max(Integer::compareTo)  // Encontra o maior número
                .orElse(0);  // Valor padrão caso não haja números primos

        System.out.println("O maior número primo da lista é: " + maiorPrimo);
    }

    public static boolean isPrimo(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

// Desafio 17
class Desafio17 {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

        List<Integer> primos = numeros.stream()
                .filter(Desafio17::isPrimo)
                .toList();

        System.out.println("Números primos na lista: " + primos);
    }

    public static boolean isPrimo(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}


