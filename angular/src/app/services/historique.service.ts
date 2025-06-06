import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { History } from '../models/history.model';



@Injectable({
  providedIn: 'root'
})
export class HistoriqueService {
  private apiUrl = 'http://localhost:8081/api/historique';

  constructor(private http: HttpClient) {}

  getAll(): Observable<History[]> {
    return this.http.get<History[]>(this.apiUrl);
  }

  getMyActions() {
  return this.http.get<any[]>('http://localhost:8081/api/historique/me');
}

getLast7Days() {
  return this.http.get<{ day: string; count: number }[]>('http://localhost:8081/api/historique/last-7-days');
}


}
