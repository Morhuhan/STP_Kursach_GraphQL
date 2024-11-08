--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: cart; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cart (
    id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.cart OWNER TO postgres;

--
-- Name: cart_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cart_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cart_id_seq OWNER TO postgres;

--
-- Name: cart_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cart_id_seq OWNED BY public.cart.id;


--
-- Name: cartitem; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cartitem (
    id bigint NOT NULL,
    cart_id bigint NOT NULL,
    product_id bigint NOT NULL,
    quantity integer NOT NULL
);


ALTER TABLE public.cartitem OWNER TO postgres;

--
-- Name: cartitem_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cartitem_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cartitem_id_seq OWNER TO postgres;

--
-- Name: cartitem_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cartitem_id_seq OWNED BY public.cartitem.id;


--
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.category OWNER TO postgres;

--
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.category_id_seq OWNER TO postgres;

--
-- Name: category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;


--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    price numeric(10,2) NOT NULL,
    image_url character varying(255),
    description text,
    rating numeric(3,2),
    category_id bigint,
    in_stock boolean DEFAULT true
);


ALTER TABLE public.product OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.product_id_seq OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- Name: review; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.review (
    id bigint NOT NULL,
    content text NOT NULL,
    rating numeric(2,1) NOT NULL,
    product_id bigint NOT NULL,
    author_id bigint NOT NULL,
    CONSTRAINT review_rating_check CHECK (((rating >= (0)::numeric) AND (rating <= (5)::numeric)))
);


ALTER TABLE public.review OWNER TO postgres;

--
-- Name: review_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.review_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.review_id_seq OWNER TO postgres;

--
-- Name: review_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.review_id_seq OWNED BY public.review.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: cart id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart ALTER COLUMN id SET DEFAULT nextval('public.cart_id_seq'::regclass);


--
-- Name: cartitem id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cartitem ALTER COLUMN id SET DEFAULT nextval('public.cartitem_id_seq'::regclass);


--
-- Name: category id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- Name: review id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review ALTER COLUMN id SET DEFAULT nextval('public.review_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: cart; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cart (id, user_id) FROM stdin;
1	1
2	2
\.


--
-- Data for Name: cartitem; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cartitem (id, cart_id, product_id, quantity) FROM stdin;
3	2	5	1
7	1	19	1
8	1	3	2
2	1	4	7
9	1	2	1
1	1	1	14
4	1	5	4
10	1	6	1
11	1	7	1
12	1	8	4
13	1	9	1
14	1	10	1
15	1	11	1
5	1	12	2
6	1	13	3
16	1	14	1
17	1	15	1
18	1	16	1
19	2	17	1
20	2	18	1
21	1	21	1
22	1	22	1
\.


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (id, name) FROM stdin;
1	Electronics
2	Books
3	Clothing
4	Home & Kitchen
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (id, name, price, image_url, description, rating, category_id, in_stock) FROM stdin;
19	Sofa	599.99	\N	Comfortable three-seater sofa.	4.40	4	t
20	Microwave Oven	99.99	\N	Compact microwave oven.	4.20	4	t
23	Notebook	2.99	\N	College-ruled spiral notebook.	4.10	2	t
24	Sneakers	69.99	\N	Comfortable running shoes.	4.30	3	t
25	Bed Sheets	39.99	\N	100% cotton bed sheet set.	4.50	4	t
26	Electric Kettle	29.99	\N	Stainless steel electric kettle.	4.40	4	t
27	Tablet	329.99	\N	10-inch tablet with high-resolution display.	4.60	1	t
28	Wireless Earbuds	149.99	\N	True wireless earbuds with charging case.	4.50	1	t
29	Biography Book	18.99	\N	Inspiring biography of a historical figure.	4.20	2	t
30	Skirt	39.99	\N	Casual summer skirt.	4.10	3	t
31	Desk Lamp	24.99	\N	LED desk lamp with adjustable brightness.	4.30	4	t
32	Backpack	59.99	\N	Durable backpack with multiple compartments.	4.50	3	t
1	Smartphone	699.99		Latest model smartphone with advanced features.	4.50	1	f
2	Laptop	1299.99		High-performance laptop suitable for gaming and work.	4.70	1	f
3	Novel Book	19.99		An intriguing novel by a famous author.	4.20	2	f
4	T-Shirt	9.99		Comfortable cotton t-shirt in various sizes.	4.00	3	f
5	Blender	49.99		Powerful blender perfect for smoothies.	4.30	4	f
6	Headphones	199.99	\N	Noise-cancelling over-ear headphones.	4.60	1	f
7	E-reader	129.99	\N	Lightweight e-reader with adjustable backlight.	4.50	1	f
8	Wireless Mouse	29.99	\N	Ergonomic wireless mouse with USB receiver.	4.30	1	f
9	Science Fiction Book	15.99	\N	A thrilling sci-fi adventure novel.	4.10	2	f
10	Cookbook	25.99	\N	Recipes from around the world.	4.50	2	f
11	Jeans	49.99	\N	Classic denim jeans with a slim fit.	4.20	3	f
12	Jacket	89.99	\N	Water-resistant outdoor jacket.	4.40	3	f
13	Coffee Maker	79.99	\N	Automatic coffee maker with timer.	4.50	4	f
14	Vacuum Cleaner	149.99	\N	High-suction vacuum cleaner.	4.30	4	f
15	Smartwatch	249.99	\N	Fitness tracking smartwatch.	4.20	1	f
16	Keyboard	59.99	\N	Mechanical keyboard with backlight.	4.60	1	f
17	Fantasy Book	22.99	\N	An epic fantasy tale.	4.70	2	f
18	Dress	79.99	\N	Elegant evening dress.	4.50	3	f
21	Gaming Console	399.99	\N	Latest generation gaming console.	4.80	1	f
22	Camera	499.99	\N	Digital SLR camera with lens.	4.70	1	f
\.


--
-- Data for Name: review; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.review (id, content, rating, product_id, author_id) FROM stdin;
1	Amazing smartphone with great battery life.	5.0	1	1
2	A captivating read from start to finish.	4.5	3	2
3	aaa	0.2	1	1
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, username, password) FROM stdin;
1	user1	$2a$10$SOUdFEPYd58Vgut6hzQCDOGfsqjPmPSsC0bPmurke65VpEx8qZHs6
2	user2	$2a$10$.2Ri5MA191V.sFTevIHNse5238All9dirq3Qxyk9x50c6409JMSJe
\.


--
-- Name: cart_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cart_id_seq', 2, true);


--
-- Name: cartitem_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cartitem_id_seq', 22, true);


--
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_id_seq', 4, true);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 5, true);


--
-- Name: review_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.review_id_seq', 3, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 2, true);


--
-- Name: cart cart_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_pkey PRIMARY KEY (id);


--
-- Name: cart cart_user_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_user_id_key UNIQUE (user_id);


--
-- Name: cartitem cartitem_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cartitem
    ADD CONSTRAINT cartitem_pkey PRIMARY KEY (id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: review review_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_pkey PRIMARY KEY (id);


--
-- Name: cartitem uq_cartitem_cart_product; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cartitem
    ADD CONSTRAINT uq_cartitem_cart_product UNIQUE (cart_id, product_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- Name: cart fk_cart_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT fk_cart_user FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- Name: cartitem fk_cartitem_cart; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cartitem
    ADD CONSTRAINT fk_cartitem_cart FOREIGN KEY (cart_id) REFERENCES public.cart(id) ON DELETE CASCADE;


--
-- Name: cartitem fk_cartitem_product; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cartitem
    ADD CONSTRAINT fk_cartitem_product FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: review fk_review_author; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT fk_review_author FOREIGN KEY (author_id) REFERENCES public.users(id);


--
-- Name: review fk_review_product; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT fk_review_product FOREIGN KEY (product_id) REFERENCES public.product(id) ON DELETE CASCADE;


--
-- Name: product product_category_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- PostgreSQL database dump complete
--

