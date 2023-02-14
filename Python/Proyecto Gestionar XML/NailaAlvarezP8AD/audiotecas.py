import xml.etree.ElementTree as ET
from xml.etree.ElementTree import ElementTree
from tkinter import *
from tkinter import PhotoImage
from tkinter import messagebox
from tkinter import ttk
import sys
from PIL import Image, ImageTk
from tkinter.filedialog import askopenfilename

app = Tk()
app.configure(bg="azure")
app.geometry("500x300")

#Declaración de las imagenes
iconoGestionar = Image.open('src\\icono_gestionar.png')
iconoGestionar = iconoGestionar.resize((80, 60))
iconoGestionar = ImageTk.PhotoImage(iconoGestionar)

iconoCrear = Image.open('src\\icono_crear.png')
iconoCrear = iconoCrear.resize((50, 40))
iconoCrear = ImageTk.PhotoImage(iconoCrear)

iconoModificar = Image.open('src\\icono_modificar2.png')
iconoModificar = iconoModificar.resize((60,50))
iconoModificar = ImageTk.PhotoImage(iconoModificar)

iconoAceptar = Image.open('src\\done.png')
iconoAceptar = iconoAceptar.resize((30,30))
iconoAceptar = ImageTk.PhotoImage(iconoAceptar)

iconoCancelar = Image.open('src\\close.png')
iconoCancelar = iconoCancelar.resize((30,30))
iconoCancelar = ImageTk.PhotoImage(iconoCancelar)

iconoMostrar = Image.open('src\\icono_mostrar.png')
iconoMostrar = iconoMostrar.resize((80, 50))
iconoMostrar = ImageTk.PhotoImage(iconoMostrar)

# Menu gestionar audiotecas
# Se compone de un titulo y dos botones. Uno para mostrar audioteca y el otro para modificarla
def envia_gestionar():
    ventana_gestionar = Toplevel()
    ventana_gestionar.geometry("500x300")
    ventana_gestionar.title("Gestionar audiotecas")
    ventana_gestionar.config(bg="azure")

    marco1 = Frame(ventana_gestionar)
    marco1.pack()
    marco1.config(width="400", height="400")
    marco1.config(bg="azure")

    titulo = Label(marco1, text="Gestionar audiotecas", font="normal 15 bold", bg="light blue", width=30, height=2, borderwidth=2,relief = "solid").grid(row=0, pady=15)

    mostrar = Button(marco1, text="Mostrar audioteca", image=iconoMostrar, compound="left", activebackground="cadet blue",
                width=250, height=70, font="15", command=envia_mostrar, borderwidth=1,relief = "solid", bg="powder blue").grid(row=1, column=0, pady=10)

    modificar = Button(marco1, text="Modificar audioteca", image=iconoModificar, compound="left", activebackground="cadet blue",
                width=250, height=70, font="15", command=envia_modificar, borderwidth=1,relief = "solid", bg="powder blue").grid(row=2, column=0)

# Obtiene el id del disco
def obtener_id_disco(nombre_fichero):
    
    try:
        xml_file = open(nombre_fichero)
        if xml_file.readable():
            xml_data = ET.fromstring(xml_file.read())
            tree = ElementTree()
            tree.parse(nombre_fichero)

            for disco in tree.findall('disco'):
                id_disco = disco.get('id')
                print(id_disco)
        else:
            print(False)
            
    except FileNotFoundError:
        id_disco = "0"
        #print("entra en la excepción")
    return id_disco

# Recoge los datos de los Entry y los envía a la función add_disco para crear el disco
def crear_disco2(audioteca, nombre_fichero):
    ventana_crear = Toplevel()
    ventana_crear.geometry("450x450")
    ventana_crear.title("Crear audioteca")
    ventana_crear.config(bg="azure")

    id_disco = obtener_id_disco(nombre_fichero + ".xml")
    id_disco = str(int(id_disco) + 1)

    nombre_disco = StringVar()
    year_disco = StringVar()
    artista_disco = StringVar()
    genero_disco = StringVar()

    label_nombre = Label(ventana_crear, text="Introduce el nombre del disco",
                        bg="azure", font="8").grid(row=0, column=0, sticky="nw", padx=50)

    insert_nombre = Entry(ventana_crear, textvariable=nombre_disco, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=1, column=0, pady = 10, sticky="nw", padx=50)

    label_year = Label(ventana_crear, text="Introduce el año del disco",
                        bg="azure", font="8").grid(row=2, column=0, sticky="nw", padx=50)

    insert_year = Entry(ventana_crear, textvariable=year_disco, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=3, column=0, pady = 10, sticky="nw", padx=50)

    label_artista = Label(ventana_crear, text="Introduce el artista del disco",
                        bg="azure", font="8").grid(row=4, column=0, sticky="nw", padx=50)

    insert_artista = Entry(ventana_crear, textvariable=artista_disco, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=5, column=0, pady = 10, sticky="nw", padx=50)

    label_genero = Label(ventana_crear, text="Introduce el género del disco",
                        bg="azure", font="8").grid(row=6, column=0, sticky="nw", padx=50)

    insert_genero = Entry(ventana_crear, textvariable=genero_disco, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=7, column=0, pady = 10, sticky="nw", padx=50)
    
    aceptar= Button(ventana_crear, text="Aceptar", image=iconoAceptar, compound="left", activebackground="cadet blue",
                    command=lambda:[add_disco(audioteca, nombre_fichero, nombre_disco.get(), year_disco.get(), artista_disco.get(), genero_disco.get()), ventana_crear.destroy()],
                    width=130, height=60, font="15",borderwidth=1,relief = "solid", bg="powder blue").grid(row=8, column=0)


 # Crear la canción con su titulo y su duración
def add_cancion(audioteca, disco, nombre_fichero, array_titulo, array_duracion):
    ventana_crear = Toplevel()
    ventana_crear.geometry("400x300")
    ventana_crear.title("Crear audioteca")
    ventana_crear.config(bg="azure")

    id_disco = obtener_id_disco(nombre_fichero + ".xml") #devuelve el id del último disco. Si no existe el fichero porque se está creando devulve el id 1
    id_disco = str(int(id_disco) + 1)
    i = 1
    j=0
    for titulo in array_titulo:
        cancion = ET.SubElement(disco, "cancion", id_disco=id_disco, id = str(i), titulo = titulo.get(), duracion = array_duracion[j].get())
        i+=1
        j+=1
        
    tree = ET.ElementTree(audioteca)
    tree.write(nombre_fichero + ".xml")

    messagebox.showinfo("Información", "Creado correctamente")

    label_crear = Label(ventana_crear, text="¿Quiere crear más discos?", font="normal 15 bold", bg="light blue", width=25,height=2, borderwidth=2,relief = "solid").grid(row=0, pady=15, padx=40)

    aceptar= Button(ventana_crear, text="Crear disco", image=iconoAceptar, compound="left", activebackground="cadet blue",
                    command=lambda:[crear_disco2(audioteca, nombre_fichero), ventana_crear.destroy()],
                    width=150, height=60, font="15",borderwidth=1,relief = "solid", bg="powder blue").grid(row=1, column=0, pady=10)

    cancelar= Button(ventana_crear, text="Salir", image=iconoCancelar, compound="left", activebackground="cadet blue",
                    command=lambda:[ventana_crear.destroy()],
                    width=150, height=60, font="15",borderwidth=1,relief = "solid", bg="powder blue").grid(row=2, column=0)


# Recoge los Entry y los envía a la función add_cancion
def crear_cancion(audioteca, disco, nombre_fichero, num_canciones):
    ventana_crear = Toplevel()
    ventana_crear.geometry("450x500")
    ventana_crear.title("Crear audioteca")
    ventana_crear.config(bg="azure")

    main_frame = Frame(ventana_crear)
    main_frame.config(bg="azure")
    main_frame.pack(fill=BOTH, expand=1)

    my_canvas = Canvas(main_frame)
    my_canvas.pack(side=LEFT, fill=BOTH, expand=1)

    my_scrollbar = ttk.Scrollbar(main_frame, orient=VERTICAL, command=my_canvas.yview)
    my_scrollbar.pack(side=RIGHT, fill=Y)

    my_canvas.configure(yscrollcommand=my_scrollbar.set)
    my_canvas.bind('<Configure>', lambda e: my_canvas.configure(scrollregion = my_canvas.bbox("all")))

    second_frame = Frame(my_canvas)
    second_frame.config(bg="azure")

    my_canvas.create_window((0,0), window=second_frame, anchor="nw")

    titulo = StringVar()
    duracion = StringVar()

    array_titulo = []
    array_duracion = []

    for i in range (int(num_canciones)):

        label_titulo = Label(second_frame, text="Introduzca el titulo de la cancion nº " + str(i + 1) ,
                            bg="azure", font="8") #grid(row=0, column=0, sticky="nw", padx=20)
        label_titulo.pack(padx=50, pady = 10)

        insert_titulo = Entry(second_frame, width=30, highlightthickness=2, highlightbackground="powder blue", 
                            highlightcolor="powder blue", font="8") #grid(row=1, column=0, pady = 10, sticky="nw", padx=20)
        insert_titulo.pack(padx=50)
        
        label_duracion = Label(second_frame, text="Introduzca la duracion",
                            bg="azure", font="8") #grid(row=2, column=0, sticky="nw", padx=20)
        label_duracion.pack(padx=50, pady = 10)

        insert_duracion = Entry(second_frame,width=30, highlightthickness=2, highlightbackground="powder blue", 
                            highlightcolor="powder blue", font="8") #grid(row=3, column=0, pady = 10, sticky="nw", padx=20)
        insert_duracion.pack(padx=50)

        array_titulo.append(insert_titulo)
        array_duracion.append(insert_duracion)
        

    aceptar= Button(second_frame, text="Crear", image=iconoAceptar, compound="left", activebackground="cadet blue",
                    command=lambda:[add_cancion(audioteca, disco, nombre_fichero, array_titulo, array_duracion), ventana_crear.destroy()],
                    width=130, height=60, font="15",borderwidth=1,relief = "solid", bg="powder blue").pack(pady=15) #grid(row=4, column=0)


#Crear el disco con los paramentros que le llegan
def add_disco(audioteca,nombre_fichero, nombre_disco, year_disco, artista_disco, genero_disco):
    ventana_crear = Toplevel()
    ventana_crear.geometry("450x270")
    ventana_crear.title("Crear audioteca")
    ventana_crear.config(bg="azure")

    id_disco = obtener_id_disco(nombre_fichero + ".xml")
    id_disco = str(int(id_disco) + 1)

    disco = ET.SubElement(audioteca, "disco", id=id_disco, nombre=nombre_disco, year=year_disco, artista=artista_disco, genero=genero_disco)

    num_cancion = StringVar()

    label_num_canciones = Label(ventana_crear, text="Introduzca cuantas canciones tiene el disco",
                        bg="azure", font="8").grid(row=0, column=0, sticky="nw", padx=40, pady = 10)

    insert_num_canciones = Entry(ventana_crear, textvariable=num_cancion, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=3, column=0, pady = 10, sticky="nw", padx=40)
                    

    aceptar= Button(ventana_crear, text="Continuar", image=iconoAceptar, compound="left", activebackground="cadet blue",
                    command=lambda:[crear_cancion(audioteca, disco, nombre_fichero, num_cancion.get()), ventana_crear.destroy()],
                    width=130, height=60, font="15",borderwidth=1,relief = "solid", bg="powder blue").grid(row=4, column=0, pady=10)

def crear_disco(nombre_fichero):

    ventana_crear = Toplevel()
    ventana_crear.geometry("450x450")
    ventana_crear.title("Crear audioteca")
    ventana_crear.config(bg="azure")

    nombre_disco = StringVar()
    year_disco = StringVar()
    artista_disco = StringVar()
    genero_disco = StringVar()

    audioteca = ET.Element("audioteca")

    label_nombre = Label(ventana_crear, text="Introduce el nombre del primer disco",
                        bg="azure", font="8").grid(row=0, column=0, sticky="nw", padx=50)

    insert_nombre = Entry(ventana_crear, textvariable=nombre_disco, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=1, column=0, pady = 10, sticky="nw", padx=50)

    label_year = Label(ventana_crear, text="Introduce el año del disco",
                        bg="azure", font="8").grid(row=2, column=0, sticky="nw", padx=50)

    insert_year = Entry(ventana_crear, textvariable=year_disco, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=3, column=0, pady = 10, sticky="nw", padx=50)

    label_artista = Label(ventana_crear, text="Introduce el artista del disco",
                        bg="azure", font="8").grid(row=4, column=0, sticky="nw", padx=50)

    insert_artista = Entry(ventana_crear, textvariable=artista_disco, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=5, column=0, pady = 10, sticky="nw", padx=50)

    label_genero = Label(ventana_crear, text="Introduce el género del disco",
                        bg="azure", font="8").grid(row=6, column=0, sticky="nw", padx=50)

    insert_genero = Entry(ventana_crear, textvariable=genero_disco, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=7, column=0, pady = 10, sticky="nw", padx=50)
    
    aceptar= Button(ventana_crear, text="Aceptar", image=iconoAceptar, compound="left", activebackground="cadet blue",
                    command=lambda:[add_disco(audioteca, nombre_fichero, nombre_disco.get(), year_disco.get(), artista_disco.get(), genero_disco.get()), ventana_crear.destroy()],
                    width=130, height=60, font="15",borderwidth=1,relief = "solid", bg="powder blue").grid(row=8, column=0, pady=10)

# Pide el nombre de la audioteca y envía ese datos a la función crear_disco
def envia_crear():
    ventana_crear = Toplevel()
    ventana_crear.geometry("420x250")
    ventana_crear.title("Crear audioteca")
    ventana_crear.config(bg="azure")

    nombre_fichero = StringVar()
    
    label_fichero = Label(ventana_crear, text="Introduce el nombre de la audioteca",
                        bg="azure", font="8").grid(row=0, column=0, sticky="w", padx=30, pady=10)

    insert_fichero = Entry(ventana_crear, textvariable=nombre_fichero, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=1, column=0, pady = 10, sticky="nw", padx=30)

    aceptar= Button(ventana_crear, text="Crear", image=iconoAceptar, compound="left", activebackground="cadet blue",
                    command=lambda:[crear_disco(nombre_fichero.get()), ventana_crear.destroy()],
                    width=130, height=60, font="15",borderwidth=1,relief = "solid", bg="powder blue").grid(row=4, column=0, pady=10)


#Modifica el nombre del disco
def modificar_nombre_disco_2(get_id, nuevo_nombre, tree, filename):
    
    for disco in tree.findall('disco'):
        if(disco.get('id') == get_id):
            #print("Aqui tiene que modificar")
            disco.set('nombre', nuevo_nombre)
            modificado = True
            break
        else:
            modificado = False
    if(modificado):
        tree.write(filename)
        messagebox.showinfo("Información", "Modificado correctamente\nPara ver los resultados debe abrir el fichero desde\n laopción mostrar fichero.")
    else:
        messagebox.showerror("Información", "El ID no es correcto")
    

# Recoge el ID del disco que quiere modificar y el nuevo nombre del disco
def modificar_nombre_disco(tree, filename):
    ventana_nombre = Toplevel()
    ventana_nombre.geometry("500x350")
    ventana_nombre.title("Modificar nombre")
    ventana_nombre.config(bg="azure")

    get_id = StringVar()
    nuevo_nombre = StringVar()

    label_id = Label(ventana_nombre, text="Introduce el ID del disco que quiere modificar",
                        bg="azure", font="8").grid(row=0, column=0, sticky="nw", padx=20)

    insert_id = Entry(ventana_nombre, textvariable=get_id, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=1, column=0, pady = 10, sticky="nw", padx=20)

    label_nombre = Label(ventana_nombre, text="Introduce el nuevo nombre del disco",
                        bg="azure", font="8").grid(row=2, column=0, sticky="nw", padx=20)

    insert_nombre = Entry(ventana_nombre, textvariable=nuevo_nombre, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=3, column=0, pady = 10, sticky="nw", padx=20)

    
    aceptar= Button(ventana_nombre, text="Aceptar", image=iconoAceptar, compound="left", activebackground="cadet blue",
                    command=lambda:[modificar_nombre_disco_2(get_id.get(), nuevo_nombre.get(), tree, filename), ventana_nombre.destroy()],
                    width=130, height=60, font="15",borderwidth=1,relief = "solid", bg="powder blue").grid(row=4, column=0)

#Modifica el año del disco
def modificar_year_2(get_id, nuevo_year, tree, filename):
    
    for disco in tree.findall('disco'):
        if(disco.get('id') == get_id):
            #print("Aqui tiene que modificar")
            disco.set('year', nuevo_year)
            modificado = True
            break
        else:
            modificado = False
    if(modificado):
        tree.write(filename)
        messagebox.showinfo("Información", "Modificado correctamente\nPara ver los resultados debe abrir el fichero desde\n laopción mostrar fichero.")
    else:
        messagebox.showerror("Información", "El ID no es correcto")

# Recoge el ID del disco que quiere modificar y el nuevo año del disco   
def modificar_year(tree, filename):
    ventana_year = Toplevel()
    ventana_year.geometry("500x350")
    ventana_year.title("Modificar año")
    ventana_year.config(bg="azure")

    get_id = StringVar()
    nuevo_year = StringVar()

    label_id = Label(ventana_year, text="Introduce el ID del disco que quiere modificar",
                        bg="azure", font="8").grid(row=0, column=0, sticky="nw", padx=20)

    insert_id = Entry(ventana_year, textvariable=get_id, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=1, column=0, pady = 10, sticky="nw", padx=20)

    lable_year = Label(ventana_year, text="Introduce el nuevo año del disco",
                        bg="azure", font="8").grid(row=2, column=0, sticky="nw", padx=20)

    insert_year = Entry(ventana_year, textvariable=nuevo_year, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=3, column=0, pady = 10, sticky="nw", padx=20)

    aceptar= Button(ventana_year, text="Aceptar", image=iconoAceptar, compound="left", activebackground="cadet blue",
                    command=lambda:[modificar_year_2(get_id.get(), nuevo_year.get(), tree, filename), ventana_year.destroy()],
                    width=130, height=60, font="15", borderwidth=1,relief = "solid", bg="powder blue").grid(row=4, column=0)

#Modifica el artista del disco según el ID    
def modificar_artista_2(get_id, nuevo_artista, tree, filename):
    
    for disco in tree.findall('disco'):
        if(disco.get('id') == get_id):
            #print("Aqui tiene que modificar")
            disco.set('artista', nuevo_artista)
            modificado = True
            break
        else:
            modificado = False
    if(modificado):
        tree.write(filename)
        messagebox.showinfo("Información", "Modificado correctamente\nPara ver los resultados debe abrir el fichero desde\n laopción mostrar fichero.")
    else:
        messagebox.showerror("Información", "El ID no es correcto")
# Recoge el ID del disco que quiere modificar y el nuevo nombre del disco    
def modificar_artista(tree, filename):
    ventana_artista = Toplevel()
    ventana_artista.geometry("500x350")
    ventana_artista.title("Modificar artista")
    ventana_artista.config(bg="azure")

    get_id = StringVar()
    nuevo_artista = StringVar()

    label_id = Label(ventana_artista, text="Introduce el ID del disco que quiere modificar",
                        bg="azure", font="8").grid(row=0, column=0, sticky="nw", padx=20)

    insert_id = Entry(ventana_artista, textvariable=get_id, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=1, column=0, pady = 10, sticky="nw", padx=20)

    lable_artista = Label(ventana_artista, text="Introduce el nuevo artista del disco",
                        bg="azure", font="8").grid(row=2, column=0, sticky="nw", padx=20)

    insert_artista = Entry(ventana_artista, textvariable=nuevo_artista, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=3, column=0, pady = 10, sticky="nw", padx=20)


    aceptar= Button(ventana_artista, text="Aceptar", image=iconoAceptar, compound="left", activebackground="cadet blue",
                    command=lambda:[modificar_artista_2(get_id.get(), nuevo_artista.get(), tree, filename), ventana_artista.destroy()],
                    width=130, height=60, font="15", borderwidth=1,relief = "solid", bg="powder blue").grid(row=4, column=0)

#Modifica el género del disco
def modificar_genero_2(get_id, nuevo_genero, tree, filename):
    
    for disco in tree.findall('disco'):
        if(disco.get('id') == get_id):
            #print("Aqui tiene que modificar")
            disco.set('genero', nuevo_genero)
            modificado = True
            break
        else:
            modificado = False
    if(modificado):
        tree.write(filename)
        messagebox.showinfo("Información", "Modificado correctamente\nPara ver los resultados debe abrir el fichero desde\n laopción mostrar fichero.")
    else:
        messagebox.showerror("Información", "El ID no es correcto")

# Recoge el ID del disco que quiere modificar y el nuevo genero del disco
def modificar_genero(tree, filename):
    ventana_genero = Toplevel()
    ventana_genero.geometry("500x350")
    ventana_genero.title("Modificar género")
    ventana_genero.config(bg="azure")

    get_id = StringVar()
    nuevo_genero = StringVar()

    label_id = Label(ventana_genero, text="Introduce el ID del disco que quiere modificar",
                        bg="azure", font="8").grid(row=0, column=0, sticky="nw", padx=20)

    insert_id = Entry(ventana_genero, textvariable=get_id, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=1, column=0, pady = 10, sticky="nw", padx=20)

    lable_genero = Label(ventana_genero, text="Introduce el nuevo género del disco",
                        bg="azure", font="8").grid(row=2, column=0, sticky="nw", padx=20)

    insert_genero = Entry(ventana_genero, textvariable=nuevo_genero, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=3, column=0, pady = 10, sticky="nw", padx=20)

    aceptar= Button(ventana_genero, text="Aceptar", image=iconoAceptar, compound="left", activebackground="cadet blue",
                    command=lambda:[modificar_genero_2(get_id.get(), nuevo_genero.get(), tree, filename), ventana_genero.destroy()],
                    width=130, height=60, font="15", borderwidth=1,relief = "solid", bg="powder blue").grid(row=4, column=0)

#Modifica el titulo de la canción
def modificar_titulo_cancion2(get_id, get_id_cancion, nuevo_titulo, tree, filename):
    
    for disco in tree.findall('disco'):
        for cancion in tree.iter('cancion'):
            if(cancion.get('id_disco') == get_id and cancion.get('id') == get_id_cancion):
                cancion.set('titulo', nuevo_titulo)
                modificado = True
                break
            else:
                modificado = False
    if(modificado):
        tree.write(filename)
        messagebox.showinfo("Información", "Modificado correctamente\nPara ver los resultados debe abrir el fichero desde\n laopción mostrar fichero.")
    else:
        messagebox.showerror("Información", "El ID del disco o de la canción no es correcto")

# Recoge el ID del disco que quiere modificar y el nuevo nombre dela canción
def modificar_titulo_cancion(tree, filename):
    ventana_titulo = Toplevel()
    ventana_titulo.geometry("500x350")
    ventana_titulo.title("Modificar género")
    ventana_titulo.config(bg="azure")

    get_id = StringVar()
    get_id_cancion = StringVar()
    nuevo_titulo = StringVar()

    label_id = Label(ventana_titulo, text="Introduce el ID del disco que quiere modificar",
                        bg="azure", font="8").grid(row=0, column=0, sticky="nw", padx=20)

    insert_id = Entry(ventana_titulo, textvariable=get_id, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=1, column=0, pady = 10, sticky="nw", padx=20)

    label_id_cancion = Label(ventana_titulo, text="Introduce el ID de la canción que quiere modificar",
                        bg="azure", font="8").grid(row=2, column=0, sticky="nw", padx=20)

    insert_id_cancion = Entry(ventana_titulo, textvariable=get_id_cancion, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=3, column=0, pady = 10, sticky="nw", padx=20)

    lable_artista = Label(ventana_titulo, text="Introduce el nuevo titulo de la cancion",
                        bg="azure", font="8").grid(row=4, column=0, sticky="nw", padx=20)

    insert_artista = Entry(ventana_titulo, textvariable=nuevo_titulo, width=30, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=5, column=0, pady = 10, sticky="nw", padx=20)

    aceptar= Button(ventana_titulo, text="Aceptar", image=iconoAceptar, compound="left", activebackground="cadet blue",
                    command=lambda:[modificar_titulo_cancion2(get_id.get(), get_id_cancion.get(), nuevo_titulo.get(), tree, filename), ventana_titulo.destroy()],
                    width=130, height=60, font="15",borderwidth=1,relief = "solid", bg="powder blue").grid(row=6, column=0)

# Modifica la duración de la canción
def modificar_duracion_2(get_id, get_id_cancion, nueva_duracion, tree, filename):
    
    for disco in tree.findall('disco'):
        for cancion in tree.iter('cancion'):
            if(cancion.get('id_disco') == get_id and cancion.get('id') == get_id_cancion):
                cancion.set('duracion', nueva_duracion)
                modificado = True
                break
            else:
                modificado = False
    if(modificado):
        tree.write(filename)
        messagebox.showinfo("Información", "Modificado correctamente\nPara ver los resultados debe abrir el fichero desde\n laopción mostrar fichero.")
    else:
        messagebox.showerror("Información", "El ID del disco o de la canción no es correcto")

# Recoge el ID del disco que quiere modificar y la nueva duración de la canción
def modificar_duracion(tree, filename):
    ventana_duracion = Toplevel()
    ventana_duracion.geometry("500x350")
    ventana_duracion.title("Modificar género")
    ventana_duracion.config(bg="azure")

    get_id = StringVar()
    get_id_cancion = StringVar()
    nueva_duracion = StringVar()

    label_id = Label(ventana_duracion, text="Introduce el ID del disco que quiere modificar", 
                        bg="azure", font="8").grid(row=0, column=0, sticky="nw", padx=20)

    insert_id = Entry(ventana_duracion, textvariable=get_id, width=40, highlightthickness=2, highlightbackground="powder blue", 
                        highlightcolor="powder blue", font="8").grid(row=1, column=0, pady = 10, sticky="nw", padx=20)

    label_id_cancion = Label(ventana_duracion, text="Introduce el ID de la canción que quiere modificar", 
                        bg="azure", font="8").grid(row=2, column=0, padx=20, sticky="nw")

    insert_id_cancion = Entry(ventana_duracion, textvariable=get_id_cancion, width=40, highlightthickness=2, highlightbackground="powder blue",
                        highlightcolor="powder blue", font="8").grid(row=3, column=0, pady = 10, sticky="nw", padx=20)

    lable_duracion = Label(ventana_duracion, text="Introduce el nuevo titulo de la cancion", 
                        bg="azure", font="8").grid(row=4, column=0, sticky="nw", padx=20)

    insert_duracion = Entry(ventana_duracion, textvariable=nueva_duracion, width=40, highlightthickness=2, highlightbackground="powder blue",
                        highlightcolor="powder blue", font="8").grid(row=5, column=0, pady=10, sticky="nw", padx=20)

    aceptar= Button(ventana_duracion, text="Aceptar", image=iconoAceptar, compound="left", activebackground="cadet blue",
                    command=lambda:[modificar_duracion_2(get_id.get(), get_id_cancion.get(), nueva_duracion.get(), tree, filename), ventana_duracion.destroy()],
                    width=130, height=60, font="15", borderwidth=1,relief = "solid", bg="powder blue").grid(row=6, column=0)

#Este método es igual que envia_mostrar solo que le llega el filename desde la opción modificar para que se le muestre por pantalla
#el archivo que eleigió para modificar
def mostrar(filename):
    ventana_mostrar = Toplevel()
    ventana_mostrar.geometry("400x500")
    ventana_mostrar.title("Audioteca sin modificar")

    text=""
    try:
        xml_file = open(filename) #Apertura del fichero
        if xml_file.readable():
            xml_data = ET.fromstring(xml_file.read()) 
            for disco in xml_data:
                text = text + "Disco ID " + disco.attrib['id'] + "\n"
                text = text + "Nombre: " + disco.attrib['nombre'] + "\n"
                text = text + "Año: " + disco.attrib['year'] + "\n"
                text = text + "Artista: " + disco.attrib['artista'] + "\n"
                text = text + "Género: " + disco.attrib['genero'] + "\n"
                text = text + "\n"
                text = text + "Canciones: \n"
                for cancion in xml_data.iter('cancion'):
                    if(disco.get('id') == cancion.get('id_disco')):
                        text= text + "Canción ID " + cancion.attrib['id'] + "\n"
                        text = text + "Título: " + cancion.attrib['titulo'] + "\n"
                        text = text + "Duración: " + cancion.attrib['duracion'] + "\n"
                text = text + "\n"
                text = text + "\n"
        main_frame = Frame(ventana_mostrar)
        main_frame.pack(fill=BOTH, expand=1)

        my_canvas = Canvas(main_frame)
        my_canvas.pack(side=LEFT, fill=BOTH, expand=1)

        my_scrollbar = ttk.Scrollbar(main_frame, orient=VERTICAL, command=my_canvas.yview)
        my_scrollbar.pack(side=RIGHT, fill=Y)

        my_canvas.configure(yscrollcommand=my_scrollbar.set)
        my_canvas.bind('<Configure>', lambda e: my_canvas.configure(scrollregion = my_canvas.bbox("all")))

        second_frame = Frame(my_canvas)

        my_canvas.create_window((0,0), window=second_frame, anchor="nw")
        mostrar_texto = Label(second_frame, text=text,bg="azure", font="8").grid(row=0, column=0, sticky="nw")
        
    except Exception as err:
        print("Error: ", err)
    finally:
        xml_file.close()

def envia_modificar():
    ventana_modificar = Toplevel()
    ventana_modificar.geometry("615x630")
    ventana_modificar.title("Modificar audioteca")
    ventana_modificar.config(bg="azure")

    filename = askopenfilename()
    xml_file = open(filename)
    if xml_file.readable():
        xml_data = ET.fromstring(xml_file.read())
        tree = ElementTree()
        tree.parse(filename)

    else:
        print(False)

    mostrar(filename)
    marco1 = Frame(ventana_modificar)
    marco1.pack()
    marco1.config(width="400", height="400")
    marco1.config(bg="azure")

    titulo = Label(marco1, text="Escoge la opción que quiere modificar", font="normal 15 bold", bg="light blue", width=50,height=2, borderwidth=2,relief = "solid").grid(row=0, pady=15)

    modificar_nombre = Button(marco1, text="Modificar nombre", image=iconoModificar, compound="left", anchor="w", activebackground="cadet blue",
                                width=225, height=70, font="15", command = lambda:[modificar_nombre_disco(tree, filename)],
                                borderwidth=1,relief = "solid", bg="powder blue").grid(row=1, column=0, pady=10)
    modificar_year_disco = Button(marco1, text="Modificar año", image=iconoModificar, compound="left", anchor="w", activebackground="cadet blue", 
                                width=225, height=70, font="15", command = lambda:[modificar_year(tree, filename)],
                                borderwidth=1,relief = "solid", bg="powder blue").grid(row=2, column=0)
    modificar_artista_disco = Button(marco1, text="Modificar artista",image=iconoModificar, compound="left", anchor="w", activebackground="cadet blue",
                                width=225, height=70, font="15", command = lambda:[modificar_artista(tree, filename)],
                                borderwidth=1,relief = "solid", bg="powder blue").grid(row=3, column=0, pady=10)
    modificar_genero_disco = Button(marco1, text="Modificar genero",image=iconoModificar, compound="left", anchor="w", activebackground="cadet blue",
                                width=225, height=70, font="15", command= lambda:[modificar_genero(tree,filename)],
                                borderwidth=1,relief = "solid", bg="powder blue").grid(row=4, column=0)
    modificar_titulo = Button(marco1, text="Modificar titulo", image=iconoModificar, compound="left", anchor="w", activebackground="cadet blue",
                                width=225, height=70, font="15", command= lambda:[modificar_titulo_cancion(tree, filename)],
                                borderwidth=1,relief = "solid", bg="powder blue").grid(row=5, column=0, pady=10)
    modificar_duracion_cancion = Button(marco1, text="Modificar duración", image=iconoModificar, compound="left", anchor="w", activebackground="cadet blue",
                                width=225, height=70, font="15", command= lambda:[modificar_duracion(tree, filename)],
                                borderwidth=1,relief = "solid", bg="powder blue").grid(row=6, column=0)

def envia_mostrar():
    ventana_mostrar = Toplevel()
    ventana_mostrar.geometry("400x550")
    ventana_mostrar.title("Mostrar audioteca")
    ventana_mostrar.config(bg="azure")

    text=""
    filename = askopenfilename()
    try:
        xml_file = open(filename) #Apertura del fichero
        if xml_file.readable():
            xml_data = ET.fromstring(xml_file.read()) 
            for disco in xml_data:
                text = text + "Disco ID " + disco.attrib['id'] + "\n"
                text = text + "Nombre: " + disco.attrib['nombre'] + "\n"
                text = text + "Año: " + disco.attrib['year'] + "\n"
                text = text + "Artista: " + disco.attrib['artista'] + "\n"
                text = text + "Género: " + disco.attrib['genero'] + "\n"
                text = text + "\n"
                text = text + "Canciones:" + "\n"
                for cancion in xml_data.iter('cancion'):
                    if(disco.get('id') == cancion.get('id_disco')):
                        text = text + "Canción ID " + cancion.attrib['id'] + "\n"
                        text = text + "Título: " + cancion.attrib['titulo'] + "\n"
                        text = text + "Duración: " + cancion.attrib['duracion'] + "\n"
                text = text + "\n"
                text = text + "\n"
        main_frame = Frame(ventana_mostrar)
        main_frame.pack(fill=BOTH, expand=1)

        my_canvas = Canvas(main_frame)
        my_canvas.pack(side=LEFT, fill=BOTH, expand=1)

        my_scrollbar = ttk.Scrollbar(main_frame, orient=VERTICAL, command=my_canvas.yview)
        my_scrollbar.pack(side=RIGHT, fill=Y)

        my_canvas.configure(yscrollcommand=my_scrollbar.set)
        my_canvas.bind('<Configure>', lambda e: my_canvas.configure(scrollregion = my_canvas.bbox("all")))

        second_frame = Frame(my_canvas)

        my_canvas.create_window((0,0), window=second_frame, anchor="nw")
        mostrar_texto = Label(second_frame, text=text, bg="azure", font="8").grid(row=0, column=0, sticky="w")
        
    except Exception as err:
        print("Error: ", err)
    finally:
        xml_file.close()

marco_principal = Frame()

marco_principal.pack()

marco_intermedio = Frame()
marco_intermedio.pack()

marco_principal2 = Frame()

marco_principal2.pack()

marco_principal.config(width="400", height="400")
marco_principal.config(bg="azure")

marco_intermedio.config(width="100", height="20")
marco_intermedio.config(bg="azure")

marco_principal2.config(width="400", height="400")
marco_principal2.config(bg="azure")

titulo = Label(marco_principal, text="Menú principal", font="normal 35 bold", bg="light blue", width=15, borderwidth=2,relief = "solid").grid(row=0)

gestionar = Button(marco_principal2, text="Gestionar audioteca", image=iconoGestionar, compound="left", activebackground="cadet blue",
                width=270, height=70, font="15", command=envia_gestionar,borderwidth=1,relief = "solid", bg="powder blue").grid(row=0, column=0,pady=10)

crear = Button(marco_principal2, text="Crear una audioteca", image=iconoCrear, compound="left", activebackground="cadet blue", 
                width=270, height=70, font="15", command=envia_crear,borderwidth=1,relief = "solid", bg="powder blue").grid(row=1, column=0)

app.mainloop()