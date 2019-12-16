import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from './usuario.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class UsuarioService {

  private PRIVATE_API = "http://localhost:8080/scp/private/";
  private PUBLIC_API = "http://localhost:8080/scp/public/";

  private options = {
    headers: new HttpHeaders().set('Authorization', "Bearer " + localStorage.getItem('token'))
  };

  constructor(private http: HttpClient) { }

  upload(data: any): Observable<any> {
    let formData = new FormData();
    formData.append('arquivo', data, data['name']);

    return this.http.post<any>(this.PUBLIC_API + "arquivo", formData, this.options);
  }


  list(): Observable<Usuario[]> {

    return this.http.get<Usuario[]>(this.PRIVATE_API + "usuario/listar", this.options);

  }

  buscarPorID(guidUsuario: string): Observable<Usuario> {

    return this.http.get<Usuario>(this.PRIVATE_API + "usuario/buscarPorID/" + guidUsuario, this.options);

  }

  salvar(usuario: Usuario): Observable<any> {

    let options = {
      headers: new HttpHeaders().set('Authorization', "Bearer " + localStorage.getItem('token'))
    };
    
    return this.http.post<any>
      (this.PRIVATE_API + "usuario/", usuario, options);

  }

}
