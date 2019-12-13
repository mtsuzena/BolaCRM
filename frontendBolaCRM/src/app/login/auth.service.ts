import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private usuarioAutenticado: boolean = false;

  constructor() { }

  fazerLogin(){
    // escrever codigo de autenticacao aqui
    this.usuarioAutenticado = true;
  }

  usuarioEstaAutenticado(){
    return this.usuarioAutenticado;
  }

}
