import { UsuarioService } from './../usuario.service';
import { Component, OnInit } from '@angular/core';
import { Usuario } from '../usuario.model';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.scss']
})

export class UsuarioComponent implements OnInit {

  usuarioLogado: Usuario = JSON.parse(localStorage.getItem("usuarioLogado"));

  constructor(
    private usuarioService: UsuarioService
  ) { }

  ngOnInit() {
  }

  AtualizarPerfil(){
    
    this.usuarioService.salvar(this.usuarioLogado).subscribe(
      () => {

        console.log(this.usuarioLogado);

      }
    );
    
  }

}
