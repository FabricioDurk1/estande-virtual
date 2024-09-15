export function formatCreditCardNumber(value: string){
  const masked = value.replace(/(\d{4})(\d{4})(\d{4})(\d{4})/, '$1 $2 $3 $4');
  return masked;
}

export function formatAmountToCurrency(amount: number){
  return amount.toLocaleString("pt-BR", {
    style: 'currency',
    currency: 'BRL'
  })
}

export function getFormattedExpirationDate(expirationDate: Date){
  const expirationYearFinalDigits = expirationDate.getFullYear().toString().slice(2,4);
  const formattedExpirationMonth = (expirationDate.getMonth() + 1).toString().padStart(2, '0');
  return `${formattedExpirationMonth} / ${expirationYearFinalDigits}`;
}