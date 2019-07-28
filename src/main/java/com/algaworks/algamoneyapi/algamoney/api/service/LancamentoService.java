package com.algaworks.algamoneyapi.algamoney.api.service;

import com.algaworks.algamoneyapi.algamoney.api.model.Lancamento;
import com.algaworks.algamoneyapi.algamoney.api.model.Pessoa;
import com.algaworks.algamoneyapi.algamoney.api.repository.LancamentoRepository;
import com.algaworks.algamoneyapi.algamoney.api.repository.PessoaRespository;
import com.algaworks.algamoneyapi.algamoney.api.service.exception.PessoaInexistenteOuInativoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRespository pessoaRespository;

    public Lancamento atualizar(Long codigo, Lancamento lancamento){
        Lancamento lancamentoSalvo = buscaPeloCodigo(codigo);
        BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");

        return lancamentoRepository.save(lancamentoSalvo);
    }

    private Lancamento buscaPeloCodigo(Long codigo){

        Lancamento lancamento = lancamentoRepository.findOne(codigo);

        if(lancamento == null){
            throw new EmptyResultDataAccessException(1);
        }

        return lancamento;

    }

    public Lancamento salvar(Lancamento lancamento) {

        Pessoa pessoaSalva = pessoaRespository.findOne(lancamento.getCodigoPessoa().getCodigo());

        if(pessoaSalva == null || pessoaSalva.isInativo()){
           throw new PessoaInexistenteOuInativoException();
        }

        return lancamentoRepository.save(lancamento);
    }
}
