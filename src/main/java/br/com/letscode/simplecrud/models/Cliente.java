package br.com.letscode.simplecrud.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    /**
     * Acesse por localhost:8080/swagger-ui.html
     */

    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Size(min = 1, max = 64, message = "Nome entre 1 a 64 caracteres.")
    @Column(nullable = false, length = 64)
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email(message = "Email não é válido")
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @Min(value = 18, message = "Idade deve ser superior a 18 anos")
    @Column(nullable = false)
    @NotNull(message = "Idade é obrigatória")
    private int age;

    @Size(min = 13, max = 13, message = "VAT NUMBER deve ter exatamente 13 dígitos.")
    @Column(nullable = false, unique = true, length = 13)
    @NotBlank(message = "Vatnumber é obrigatório")
    @Pattern(regexp = "[a-zA-Z]{2}[0-9]{11}", message = "Vat precisa seguir padrão XX99999999999")
    private String vatNumber;

    public void setId(Long id) {
        this.id = id; 
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;    
    }

    public int getAge() {
        return this.age;
    }

    public String getVatNumber() {
        return this.vatNumber;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", email='" + getEmail() + "'" +
            ", age='" + getAge() + "'" +
            ", vatNumber='" + getVatNumber() + "'" +
            "}";
    }

}