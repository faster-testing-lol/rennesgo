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
