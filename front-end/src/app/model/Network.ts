import { Line } from './Line';
import { Stop } from './Stop';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class Network {
    readonly url: string;
    lines: Map<string, Line>;
    stops: Stop[];

    constructor(private readonly http: HttpClient) {
        this.lines = new Map<string, Line>();
        this.url = 'https://data.rennesmetropole.fr/api/records/1.0/search/?';
        // Using ng serve, called on each compilation: flooding
        this.http
            .get(`${this.url}dataset=lignes-de-bus-du-reseau-star&rows=200`)
            .subscribe((jsonLines: any) => {
                jsonLines.records.map((rec: any) => rec.fields).forEach(line => {
                    this.lines.set(line.nomcourt, line);
                    this.lines.get(line.nomcourt).msgs = [];
                });
            });

       /*  const jsonCode = JSON.parse('[{"idbillettique":11,"idparcoursprincipalaller":"0011-A-1533-3751","couleurligne":"#ef859d","couleurtexteligne":"#1a171b","idparcoursprincipalretour":"0011-B-3751-1533","nomfamillecommerciale":"Urbaine","nomlong":"Rennes (La Poterie) <> Vezin-le-Coquet (ZI Ouest)","nomcourt":"11","id":"0011"},{"idbillettique":31,"idparcoursprincipalaller":"0031-A-2088-1190","couleurligne":"#fff164","couleurtexteligne":"#e2001a","idparcoursprincipalretour":"0031-B-1190-2088","nomfamillecommerciale":"Inter-quartiers","nomlong":"Cesson-Sévigné (Stade Dézerseul) <> Rennes (Villejean-Churchill)","nomcourt":"31","id":"0031"},{"idbillettique":3,"idparcoursprincipalaller":"0003-A-1103-1509","couleurligne":"#00893e","couleurtexteligne":"#ffffff","idparcoursprincipalretour":"0003-B-1509-1103","nomfamillecommerciale":"CHRONOSTAR","nomlong":"Rennes (Saint-Laurent) <> Rennes (Henri Fréville)","nomcourt":"C3","id":"0003"}]');
         for (const element in jsonCode) {
            this.lines.set(jsonCode[element].nomcourt, jsonCode[element]);
            this.lines.get(jsonCode[element].nomcourt).msgs = [];
        } */
    }

    /**
     * Updates the messages that concerns the given line.
     * @param linename The short name of the line.
     */
    updateMessageOf(linename: string) {
        const line: Line | undefined = this.lines.get(linename);

        if (line !== undefined) {
            this.http
            .get(`${this.url}dataset=alertes-trafic-en-temps-reel-sur-les-lignes-du-reseau-star&refine.nomcourtligne=${linename}`, {})
            .subscribe((data: any) => line.msgs = data.records.map(rec => rec.fields.description));
        }
    }

    /**
     * Returns the lines of the bus network
     */
    getLines(): Line[] {
        return [...this.lines.values()];
    }
}
