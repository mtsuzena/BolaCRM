import { MapsComponent } from './../maps/maps.component';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private usuarioAutenticado: boolean = false;
  private token: string = null;
  private id: number = null;
  user: any;
 
  constructor(private http : HttpClient) { }

  login(login: string, senha: string) : Observable<any> {
    let options = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
    };

    return this.http.post<any>
      ("http://localhost:8080/scp/public/login",
        "login="+login+"&senha="+senha, 
        options);
  }

  setUsuarioAutenticado(autenticado: boolean){
    this.usuarioAutenticado = autenticado;
  }

  usuarioEstaAutenticado(){
    return this.usuarioAutenticado;
  }

}
