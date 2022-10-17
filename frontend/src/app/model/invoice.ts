export type CreateInvoiceRequest = {
  number: string,
  description: string,
  dueTimeInDays: number,
  companyId: string,
  userId: string
}

export type InvoiceDTO = {
  id: number | null,
  number: string,
  description: string,
  creationDate: string,
  dueDate: string,
  userFullName: string,
  companyName: string
}
