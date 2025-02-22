import { UsuarioService } from './../usuario.service';
import { Component, OnInit } from '@angular/core';
import { Usuario } from '../usuario.model';
declare var $: any;

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

        this.ExibirPopup('Perfil atualizado com sucesso','success','bottom', 'center')

      }
    );
    
  }

  ExibirPopup(message: string, type: string, from: string, align: string){
    $.notify({
      icon: "notifications",
      message: message
    },
    {
      type: type,
      timer: 200,
      placement: {
          from: from,
          align: align
      },
      template: '<div data-notify="container" class="col-xl-4 col-lg-4 col-11 col-sm-4 col-md-4 alert alert-{0} alert-with-icon" role="alert">' +
        '<button mat-button  type="button" aria-hidden="true" class="close mat-button" data-notify="dismiss">  <i class="material-icons">close</i></button>' +
        '<i class="material-icons" data-notify="icon">notifications</i> ' +
        '<span data-notify="title">{1}</span> ' +
        '<span data-notify="message">{2}</span>' +
        '<div class="progress" data-notify="progressbar">' +
          '<div class="progress-bar progress-bar-{0}" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>' +
        '</div>' +
        '<a href="{3}" target="{4}" data-notify="url"></a>' +
      '</div>'
    });
  }

}
