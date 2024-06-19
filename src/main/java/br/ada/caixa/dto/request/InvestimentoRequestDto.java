package br.ada.caixa.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvestimentoRequestDto {

    private String documentoCliente;
    private BigDecimal valor;

}
