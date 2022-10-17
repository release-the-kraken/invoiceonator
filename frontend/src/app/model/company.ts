import {InvoiceDTO} from "./invoice";

export type AddCompanyRequest = {
  name: string,
  countryOfOrigin: string,
  industry: string
}

export type CompanyDTO = {
  id: number | null,
  name: string,
  countryOfOrigin: string,
  industry: string,
  userFullName: string,
  invoices: InvoiceDTO[]
}
