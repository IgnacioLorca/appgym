package es.eoi.controller;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractController <E> {
    //Literal para los numeros de pagina
    protected final String pageNumbersAttributeKey = "pageNumbers";
    //Metodo para obtener los numeros de pagina
    protected List<Integer> dameNumPaginas(Page<E> entidades){
        List<Integer> pageNumbers = new ArrayList<>();
        int totalPages = entidades.getTotalPages();
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
        }
        return pageNumbers;
    }
}

