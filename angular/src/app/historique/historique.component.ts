import { Component, OnInit } from '@angular/core';
import { HistoriqueService } from '../services/historique.service';
import { History } from '../models/history.model';
import { CommonModule } from '@angular/common';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-historique',
  standalone: true,
  imports: [CommonModule,NgFor],  // Ajoute CommonModule ici
  templateUrl: './historique.component.html',
  styleUrls: ['./historique.component.css']
})
export class HistoriqueComponent implements OnInit {
  historiques: History[] = [];

  constructor(private historiqueService: HistoriqueService) {}

  ngOnInit(): void {
    this.historiqueService.getAll().subscribe(data => {
      this.historiques = data;
    });
  }
}
