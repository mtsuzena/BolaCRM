import { UsuarioService } from '../../usuario/model/usuario.service';
import { AuthService } from './../auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { NavbarComponent } from 'app/components/navbar/navbar.component';

declare var $: any;
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {

  usuario: string;
  senha: string;

  constructor(
    private router: Router,
    private authService: AuthService,
    private usuarioService: UsuarioService
  ) { }

  ngOnInit() {
    this.authService.setUsuarioAutenticado(false);
  }

  logar(){

    this.authService.login(this.usuario,this.senha).subscribe(
      user => {
        console.log(user)
        localStorage.setItem("token",user['token']);
        localStorage.setItem("id",user['id']);
        this.authService.setUsuarioAutenticado(true);
        
        this.usuarioService.buscarPorID(localStorage.getItem("id")).subscribe(
          usuario => {
            localStorage.setItem("usuarioLogado", JSON.stringify(usuario));
          }
        );

        this.ExibirPopup('Login realizado com sucesso','success', 'bottom', 'left')
        this.router.navigate(['/dashboard']);
      }
    )

    return new Promise((resolve,reject) => {
      this.authService.login(this.usuario, this.senha).pipe(
      catchError(this.handleError)
    ).subscribe(() => resolve())
    })
    
  }

  handleError(error) {
    let errorMessage = '';
    
    $.notify({
      icon: "notifications",
      message: "Login inválido! Tente novamente"
    },
    {
      type: 'danger',
      timer: 650,
      placement: {
          from: 'bottom',
          align: 'left'
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

    return throwError(errorMessage);
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
