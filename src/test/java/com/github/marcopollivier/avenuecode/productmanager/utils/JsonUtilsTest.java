package com.github.marcopollivier.avenuecode.productmanager.utils;

import org.junit.Assert;
import org.junit.Test;

public class JsonUtilsTest {

    @Test
    public void testConvertObtToJSON() {
        CartaoCredito cc = new CartaoCredito();
        cc.token = "abc123";
        cc.cvv = "987";

        Pessoa pessoa = new Pessoa();
        pessoa.nome = "Marco";
        pessoa.cc = cc;

        Assert.assertEquals(
                "{\"nome\":\"Marco\",\"cc\":{\"token\":\"abc123\",\"cvv\":\"987\"}}",
                JsonUtils.convertToJson(pessoa));
    }

    @Test
    public void testConvertObtToJSONNoCvv() {
        CartaoCredito cc = new CartaoCredito();
        cc.token = "abc123";

        Pessoa pessoa = new Pessoa();
        pessoa.nome = "Marco";
        pessoa.cc = cc;

        Assert.assertEquals(
                "{\"nome\":\"Marco\",\"cc\":{\"token\":\"abc123\"}}",
                JsonUtils.convertToJson(pessoa));
    }

    @Test
    public void testConvertFromJsonToPessoa() {
        Pessoa pessoa = JsonUtils.convertFromJson("{\"nome\":\"Marco\",\"cc\":{\"token\":\"abc123\"}}", Pessoa.class);

        Assert.assertEquals("Marco", pessoa.nome);
        Assert.assertEquals("abc123", pessoa.cc.token);
    }

    class Pessoa {
        public String nome;
        public CartaoCredito cc;
    }

    class CartaoCredito {
        public String token;
        public String cvv;
    }

}