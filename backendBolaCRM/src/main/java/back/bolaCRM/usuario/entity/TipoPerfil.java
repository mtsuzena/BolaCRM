package back.bolaCRM.usuario.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
@Entity
public class TipoPerfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long guidTipoPerfil;
	private String descricao;
	
	@ElementCollection
    @CollectionTable(name = "PERFIL_PERMISSAO",joinColumns = @JoinColumn(name = "guidTipoPerfil") , 
    foreignKey=@ForeignKey(name = "FK_PERFIL_ROLES"))
    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
	@Fetch(FetchMode.SUBSELECT)
    private List<Permissao> permissoes;
	
    public Long getGuidTipoPerfil() {
		return guidTipoPerfil;
	}
	public void setGuidTipoPerfil(Long guidTipoPerfil) {
		this.guidTipoPerfil = guidTipoPerfil;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Permissao> getPermissoes() {
		return permissoes;
	}
	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
}