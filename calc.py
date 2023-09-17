def Calculation():
  num1 = float(input("Enter a number: "))
  num2 = float(input("Enter another number: "))
  operation = input("Enter an operation (addition, subtraction, multiplication, division): ")

  def Addition(): ##This function performs the addition calculation
    sum = num1 + num2
    print(f"{num1} + {num2} = {sum}")

  def Subtraction(): ##This function performs the subtraction calculation
    difference = num1 - num2
    print(f"{num1} - {num2} = {difference}")
  
  def Multiplication(): ##This function performs the multiplication calculation
    product = num1 * num2
    print(f"{num1} * {num2} = {product}")

  def Division(): ##This function performs the multiplication calculation
    quotient = num1/num2
    print(f"{num1} / {num2} = {quotient}")
  
  ##This set of code determines what operation is selected
  if operation != False:
    if operation == "addition":
      Addition()
    elif operation == "subtraction":
      Subtraction()
    elif operation == "multiplication":
      Multiplication()
    elif operation == "division":
      if num2 == 0:
        while num2 == 0:
          num2 = float(input("You cannot divide by 0! Enter another number: "))
      Division()
      #This determines whether division is possible based on whether someone is trying to divide by 0 or not
    else:
      print("That's not a proper response.")

Calculation()
question = input("Do you want to perform another calculation (yes/no)? ")

while True: #This loop asks if the user wants to continue making calculations
  if question == "yes":
    Calculation()
    question = input("Do you want to perform another calculation (yes/no)? ")
  elif question == "no":
    print("Goodbye!")
    break
  else:
    print("That's not a proper response.")
    question = input("Do you want to perform another calculation (yes/no)? ")